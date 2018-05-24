package com.ipplus360.web;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.Organization;
import com.ipplus360.service.OrganizationService;
import com.ipplus360.util.IPUtil;
import com.ipplus360.util.OrderUtil;
import com.ipplus360.util.SessionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Organization organizations;
	@Autowired
	private OrganizationService organizationService;

	@Value("${upload.dir}")
	private String uploadRootPath;
	
//	private String realFileUploadRoot = "F:\\ipplus360mall\\src\\main\\webapp\\WEB-INF\\";
//	private String fileUploadDir ="\\upload\\";

	/**
	 * 实名认证协议
	 * @return 实名认证协议
	 */
	@RequestMapping(value = "/protocol", method = RequestMethod.GET)
	public String showProtocol() {
		return "realName";
	}
	
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String pay(HttpServletRequest request, HttpSession session, Model model, Organization organization,
					  @RequestParam(value = "file")  MultipartFile file, final RedirectAttributes redirectAttributes){

		try{

			String userIP = IPUtil.getUserIP(request);
			SessionUser user = SessionUtil.getSessionUser(request);
			redirectAttributes.addFlashAttribute("organization", organization);
			model.addAttribute("validate_succ", "1");
			session.setAttribute("content", "org");
			logger.info("登录用户为:{},登录IP为:{}",user.getEmail(),userIP);

			if(null == organization){
				model.addAttribute("msg", "企业信息为空");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}
			if(StringUtils.isEmpty(organization.getOrgName())){
				model.addAttribute("msg", "公司名称为空");
				return "redirect:/info";
			}
			if(StringUtils.isEmpty(organization.getIndustry())){
				model.addAttribute("msg", "所属行业为空");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}
			if(StringUtils.isEmpty(organization.getLicenseAddress())){
				model.addAttribute("msg", "营业执照所在地为空");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}
			if(StringUtils.isEmpty(organization.getLicenseNumber())){
				model.addAttribute("msg", "营业执照号/统一社会信用代码为空");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}
			if(null == organization.getLicenseStartDate() || "".equals(organization.getLicenseStartDate())){
				model.addAttribute("msg", "营业执照有效期/营业期限,开始时间为空");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}
			if(null == organization.getLicenseEndDate() || "".equals(organization.getLicenseEndDate())){
				model.addAttribute("msg", "营业执照有效期/营业期限,结束时间为空");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}

			organizations = organizationService.getByOrgName(organization.getOrgName());
			if(null != organizations){
				logger.info("Organizations:{}",organizations);
				model.addAttribute("msg","公司已认证或者正在认证中");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}

			List<Organization> org = organizationService.getByUserId(user.getId());
			if(null != org && !org.isEmpty() && 0 < org.size()){
				logger.info("Organizations:{}",org);
				model.addAttribute("msg","公司已认证或者正在认证中");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}

			if (file.isEmpty()) {
				model.addAttribute("msg", "上传文件为空");
				model.addAttribute("company",organization);
				return "redirect:/info";
			}else{
				//获取文件后缀名
				String lastName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

				if(StringUtils.isEmpty(lastName) || !regFilename(file.getOriginalFilename())){
					model.addAttribute("msg", "不允许文件类型");
					model.addAttribute("company",organization);
					return "redirect:/info";
				}

				if(file.getSize()>1024*1024*2){
					model.addAttribute("msg", "文件大小超过限制");
					model.addAttribute("company",organization);
					return "redirect:/info";
				}

				//创建文件目录
				request.getServletContext().getContextPath();
				String filePath = uploadRootPath +"/"+ OrderUtil.getNowDate();
				File f = new File(filePath);
				if (!f.exists() && !f.isDirectory()) {
					f.mkdirs();
				}
				String uuid = OrderUtil.getUUID32();
				String fileName = uuid + lastName;
				//文件以流的形式拷贝到目录
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, fileName));
				logger.info("文件名称:{}",filePath+"/"+fileName);
				organization.setLicenseDir(filePath+"/"+fileName);
				organization.setToken(uuid);
			}
			
			//企业再次认证信息修改
			Organization orgOld = organizationService.getOneByeUserId(user.getId());
			if(orgOld != null){
				organizationService.deleteOrgByUserId(user.getId());
			}

			organization.setAvailable(0);
			organization.setUserId(user.getId());
			organizationService.insert(organization);
			return "redirect:/info";
		}catch(Exception e){
			e.printStackTrace();
			logger.error("msg", e);
			return "redirect:/info";
		}
	}

	@RequestMapping(value = "/license", method = RequestMethod.GET)
	public String license() {

		return "";
	}

	/*@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public Result submit(HttpServletRequest request, HttpSession session, Organization organization,
						 @RequestParam(value = "file")  MultipartFile file){
		try {
			String userIP = IPUtil.getUserIP(request);
			SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);

			logger.info("登录用户为:{},登录IP为:{}", user.getEmail(), userIP);

			if (null == organization) {
				return new Result(false, "企业信息为空");
			}
			if (StringUtils.isEmpty(organization.getOrgName())) {
				return new Result(false, "公司名称为空");
			}
			if (StringUtils.isEmpty(organization.getIndustry())) {
				return new Result(false, "所属行业为空");
			}
			if (StringUtils.isEmpty(organization.getLicenseAddress())) {
				return new Result(false, "营业执照所在地为空");
			}
			if (StringUtils.isEmpty(organization.getLicenseNumber())) {
				return new Result(false, "营业执照号/统一社会信用代码为空");
			}
			if (null == organization.getLicenseStartDate() || "".equals(organization.getLicenseStartDate())) {
				return new Result(false, "营业执照有效期/营业期限,开始时间为空");
			}
			if (null == organization.getLicenseEndDate() || "".equals(organization.getLicenseEndDate())) {
				return new Result(false, "营业执照有效期/营业期限,结束时间为空");
			}

			organizations = organizationService.getByOrgName(organization.getOrgName());
			if (null != organizations) {
				logger.info("Organizations:{}", organizations);
				return new Result(false, "公司已认证或者正在认证中");
			}

			List<Organization> org = organizationService.getByUserId(user.getId());
			if (null != org && !org.isEmpty() && 0 < org.size()) {
				logger.info("Organizations:{}", org);
				return new Result(false, "公司已认证或者正在认证中");
			}

			if (file.isEmpty()) {
				return new Result(false, "上传文件为空");
			} else {
				//获取文件后缀名
				String lastName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

				if (StringUtils.isEmpty(lastName) || !regFilename(file.getOriginalFilename())) {
					return new Result(false, "不支持的文件类型");
				}

				if (file.getSize() > 1024 * 1024 * 2) {
					return new Result(false, "文件大小超过限制");
				}

				//创建文件目录
				String filePath = uploadRootPath + "/" + OrderUtil.getNowDate();
				File f = new File(filePath);
				if (!f.exists() && !f.isDirectory()) {
					f.mkdirs();
				}
				String uuid = OrderUtil.getUUID32();
				String fileName = uuid + lastName;
				//文件以流的形式拷贝到目录
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, fileName));
				logger.info("文件名称:{}", filePath + "/" + fileName);
				organization.setLicenseDir(filePath + "/" + fileName);
				organization.setToken(uuid);
			}

			organization.setAvailable(0);
			organization.setUserId(user.getId());
			organizationService.insert(organization);
			return new Result(true, "提交成功");
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("msg", e);
			return new Result(false, "上传失败");
		}
	}*/

	private boolean regFilename(String fileName){
		logger.info("用户上传文件为:{}",fileName);
		String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(fileName.toLowerCase());
		return  matcher.find();
	}
	
}
