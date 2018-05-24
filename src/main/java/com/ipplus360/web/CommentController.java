package com.ipplus360.web;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.Comment;
import com.ipplus360.service.CommentService;
import com.ipplus360.service.ProductAndPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月9日   
 */

@Controller
@RequestMapping("/comment")
public class CommentController {

	private final static Logger Logger = LoggerFactory.getLogger(CommentController.class);
	
    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductAndPriceService productAndPriceService;
    
  	@RequestMapping(value = "/product", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<Map> getCommentByProduct(@RequestParam("productId") String productId,@RequestParam("curPage") int curPage) {
		try{	
			//分页
			int PAGESIZE = 5;  
	        int pageCount;  
	        //int curPage = 0;  
			
			Map getcomment = new HashMap();
			getcomment.put("productid", productId);
			List<Comment> comment=commentService.getCommentByProduct(getcomment);
			if(null == comment){
				return new Result(false, "查询评论为空");
			}
			
			//分页
	        if(0 == curPage){
	        	curPage = 1;
	        }
            int size = comment.size();  
            pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1);
			//取每页相应的评论
            List<Comment> commentList = new ArrayList<Comment>();
            if(curPage*5 < size){
            	commentList.addAll(comment.subList((curPage-1)*5, curPage*5));
            }else{
            	commentList.addAll(comment.subList((curPage-1)*5, size));
            }
            for(int i=0;i<commentList.size();i++){
            	String email=commentList.get(i).getEmail();
            	String resultemail=email.substring(0, 2)+"*****"+email.substring(email.length()-1,email.length());
            	commentList.get(i).setEmail(resultemail);
            }
            
			Map commentResult = new HashMap();
			commentResult.put("comment", commentList);
			commentResult.put("curPage", curPage);
			commentResult.put("pageCount", pageCount);
			Result<Map> result = new Result<>(true, commentResult, "查询成功");
			//System.out.println("amountList[0]="+result.getData().indexOf(0));
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage());
			return new Result(false, "查询评论失败");
		}
    }
  	
  	
  	@RequestMapping(value = "/user", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<Map> getCommentByUser(@RequestParam("userId") String userId,@RequestParam("curPage") int curPage) {
		try{	
			//分页
			int PAGESIZE = 5;  
	        int pageCount;  
	        
			Map getcomment = new HashMap();
			getcomment.put("userid", userId);
			List<Comment> comment=commentService.getCommentByUser(getcomment);
			if(null == comment){
				return new Result(false, "查询评论为空");
			}
			
			//分页
	        if(0 == curPage){
	        	curPage = 1;
	        }
            int size = comment.size();  
            pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1);
			//取每页相应的评论
            List<Comment> commentList = new ArrayList<Comment>();
            if(curPage*5 < size){
            	commentList.addAll(comment.subList((curPage-1)*5, curPage*5));
            }else{
            	commentList.addAll(comment.subList((curPage-1)*5, size));
            }
            for(int i=0;i<commentList.size();i++){
            	String email=commentList.get(i).getEmail();
            	String resultemail=email.substring(0, 1)+"***"+email.substring(email.length()-1,email.length());
            	commentList.get(i).setEmail(resultemail);
            }
            
			Map commentResult = new HashMap();
			commentResult.put("comment", commentList);
			commentResult.put("curPage", curPage);
			commentResult.put("pageCount", pageCount);
			Result<Map> result = new Result<>(true, commentResult, "查询成功");
			//System.out.println("amountList[0]="+result.getData().indexOf(0));
			return result;
		}catch (Exception e) {
			Logger.error(e.getMessage());
			return new Result(false, "查询评论失败");
		}
    }
  	
  	
  	@RequestMapping(value = "/add", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result addComment(HttpSession session,@RequestParam("userId") String userId,
            @RequestParam("productId") String productId,@RequestParam("comment") String comment) {
		try{	
			if(session.getAttribute(Constants.SESSION_USER) == null){
				return new Result(false, "用户尚未登录");
			}

			SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);

			//System.out.println(user.getEmail());
			if(comment.trim().isEmpty()){
				return new Result(false, "评论内容为空");
			}
			
			Map getcomment = new HashMap();
			getcomment.put("productid", productId);
			getcomment.put("userid", user.getId());
			getcomment.put("status", 0);
			getcomment.put("comment", comment);
			List<Comment> usercomment = commentService.getComment(getcomment);
			if(null != usercomment && usercomment.size()>0){
				return new Result<>(false, "您已评论过该商品");
			}
			
			commentService.add(getcomment);
			//更新评论数量
			productAndPriceService.updateEvaluate(Long.valueOf(productId));
			//System.out.println("amountList[0]="+result.getData().indexOf(0));
			return new Result<>(true, "评论成功");
		}catch (Exception e) {
			Logger.error(e.getMessage());
			return new Result(false, "评论失败");
		}
    }

}
