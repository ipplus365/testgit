package com.ipplus360.service.captcha;

import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.CaptchaQuestionHelper;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.octo.captcha.image.gimpy.Gimpy;

/**
 * Created by 辉太郎 on 2017/3/6.
 */
public class CustomGimpyFactory extends ImageCaptchaFactory {
    private Random myRandom = new SecureRandom();
    private WordToImage wordToImage;
    private WordGenerator wordGenerator;
    public static final String BUNDLE_QUESTION_KEY;

    public CustomGimpyFactory(WordGenerator generator, WordToImage word2image) {
        if(word2image == null) {
            throw new CaptchaException("Invalid configuration for a GimpyFactory : WordToImage can\'t be null");
        } else if(generator == null) {
            throw new CaptchaException("Invalid configuration for a GimpyFactory : WordGenerator can\'t be null");
        } else {
            this.wordToImage = word2image;
            this.wordGenerator = generator;
        }
    }

    public ImageCaptcha getImageCaptcha() {
        return this.getImageCaptcha(Locale.getDefault());
    }

    public WordToImage getWordToImage() {
        return this.wordToImage;
    }

    public WordGenerator getWordGenerator() {
        return this.wordGenerator;
    }

    public ImageCaptcha getImageCaptcha(Locale locale) {
        Integer wordLength = this.getRandomLength();
        String word = this.getWordGenerator().getWord(wordLength, locale);
        BufferedImage image = null;

        try {
            image = this.getWordToImage().getImage(word);
        } catch (Throwable var6) {
            throw new CaptchaException(var6);
        }

        /**
         * 使用自定义类CustomGimpy
         */
        ImageCaptcha captcha = new CustomGimpy(CaptchaQuestionHelper.getQuestion(locale, BUNDLE_QUESTION_KEY), image, word);
        return captcha;
    }

    protected Integer getRandomLength() {
        int range = this.getWordToImage().getMaxAcceptedWordLength() - this.getWordToImage().getMinAcceptedWordLength();
        int randomRange = range != 0?this.myRandom.nextInt(range + 1):0;
        Integer wordLength = new Integer(randomRange + this.getWordToImage().getMinAcceptedWordLength());
        return wordLength;
    }

    static {
        BUNDLE_QUESTION_KEY = Gimpy.class.getName();
    }
}
