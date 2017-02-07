package action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pojo.Article;
import pojo.Carousel;
import pojo.Education;
import pojo.Nav;
import pojo.Profile;
import pojo.Research;
import pojo.Study;
import pojo.Studyone;
import pojo.Studytwo;
import pojo.Teacher;
import pojo.Teachergroup;
import pojo.Thesis;
import service.IArticleService;
import service.ICarouselService;
import service.IEducationService;
import service.INavService;
import service.IProfileService;
import service.IResearchService;
import service.IStudyService;
import service.IStudyoneService;
import service.IStudytwoService;
import service.ITeacherService;
import service.ITeachergroupService;
import service.IThesisService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class FrontAction extends ActionSupport{
	private IProfileService profileService;
	private INavService navService;
	private IArticleService articleService;
	private ICarouselService carouselService;
	private IStudyService studyService;
	private IStudyoneService studyoneService;
	private IStudytwoService studytwoService;
	private IEducationService educationService;
	private int id;
	private List<Profile> profileList;
	private List<Nav> navthreeList;
	private List<Nav> navfourList;
	private List<Nav> navfiveList;
	private Profile profile;
	//师资力量
	private ITeacherService teacherService;
	private ITeachergroupService teachergroupService;
	private List<Teachergroup> groupList;
	private int teaTeam_id;
	private List<Teacher> teaList;

	
	private String navName;
	private Nav nav;
	//article 中的变量
	private Article article;
	private int pageno=1;
	private int pageSize=15;
	private Pagination<Article> pagination2;
	private int nav_oneName;
	private int nav_oneName_id;
	private List<Article> articleList;
	private List<Article> newsList;
	private List<Article> lectureList;
	
	//研究院
	private IResearchService  researchService;
	private Teacher teacher;
	private Research research;
	private List<Research> researchList;
	
	//导航nav
	private String oneName;
	private List<Nav> navList;
	private int pageno1=1;
	private int pageSize1=7;
	private Pagination<Article> pagination3;
	private int pageno2=1;
	private int pageSize2=8;
	private Pagination<Article> pagination4;
	private int pageno11=1;
	private int pageSize11=3;
	private Pagination<Article> pagination31;
	
	//轮播Carousel
	private String carouselName;
	private String carouselPhoto;
	private String carouselTime;
	private String carouselContent;
	private int carouselNum;
	private int pageno3=1;
	private int pageSize3=4;
	private Pagination<Carousel> pagination5;
	private Carousel carousel;
	private List<Carousel> carouselList;
	

	private int result;
	private int teaId;
	private int resId;
	
	//study
	private List<Studyone> studyoneList;
	private List<Studytwo> studytwoList;
	private List<Study> studyList;
	private Pagination<Study> pagination6;
	private Studyone studyone;
	private Studytwo studytwo;
	private String studyOneName;
	private Study study;
	private int studyTwoName_id;
	
	//链接
	private List<Education> eduList;
	//log日志
	Log log = LogFactory.getLog(this.getClass()); 
	//论文轮播
	private IThesisService thesisService;
	private List<Thesis> thesisList;
	private Thesis thesis;
	private List<Nav> navTop;

	
	/**
	 * 导航
	 * @return
	 */
	public void nav(){
		try {
			groupList = teachergroupService.getAll();
			profileList = this.profileService.profileList();
			navTop = navService.findBynav();
			studyoneList = this.studyoneService.studyoneList();
			//test
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 主页面方法   导航栏
	 * @return
	 */
	public String index(){
		nav();
		//图片轮播
		carouselList = carouselService.carouselList();
		//  新闻通知栏       // 学术讲座
		// 在dao层 通过   数据库nav表里固定的id查询
		articleList = articleService.getArticles();
		List<Article> list = new ArrayList();
		newsList = new ArrayList();
		lectureList = new ArrayList();
		for(int i = 0;i < articleList.size();i++){
			article = articleList.get(i);
			if(article.nav_oneName == 10){
				list.add(article);
			}
			if(article.nav_oneName == 11){
				lectureList.add(article);
			}
		}
		if(list.size() > 7){
			for(int i = 0;i < 7;i++){
				newsList.add(list.get(i));
			}
		}else{
			for(int i = 0;i < list.size();i++){
				newsList.add(list.get(i));
			}
		}
		
		
		//发表论文栏
		thesisList = thesisService.getAllList();
		
		//链接
		eduList = this.educationService.eduList();
		return "index";
	}
	/**
	 * 主页中间默认页
	 */
	/*
	public String main(){
			//图片轮播
			carouselList = carouselService.carouselList();
			//  新闻通知栏       // 学术讲座
			// 在dao层 通过   数据库nav表里固定的id查询
			articleList = articleService.getArticles();
			List<Article> list = new ArrayList();
			newsList = new ArrayList();
			lectureList = new ArrayList();
			for(int i = 0;i < articleList.size();i++){
				article = articleList.get(i);
				if(article.nav_oneName == 10){
					list.add(article);
				}
				if(article.nav_oneName == 11){
					lectureList.add(article);
				}
			}
			if(list.size() > 7){
				for(int i = 0;i < 7;i++){
					newsList.add(list.get(i));
				}
			}else{
				for(int i = 0;i < list.size();i++){
					newsList.add(list.get(i));
				}
			}
			
			
			//发表论文栏
			thesisList = thesisService.getAllList();
			
			//链接
			eduList = this.educationService.eduList();
			
//			pagination5 = this.carouselService.carouselPg(pageno3, pageSize3);
			
			pagination2 = this.articleService.artTitlePg(pageno, pageSize, nav_oneName);
		
			pagination3 = this.articleService.artTitlePg(pageno1, pageSize1, nav_oneName);
			pagination31 = this.articleService.zhidingTitlePg(pageno11, pageSize11, nav_oneName);
			//学术讲座栏
			navList = this.navService.findByoneName("学术讲座");
			nav = navList.get(0);
			int nav_oneName1 = nav.getId();
			pagination4 = this.articleService.artTitlePg(pageno2, pageSize2, nav_oneName1);
			//发表论文
//			teaList = teacherService.getAllList();
			thesisList = thesisService.getAllList();
			
			//链接
			eduList = this.educationService.eduList();
		
		return "default";
	}*/
	
	/**
	 * 获取轮播内容
	 */
	public String carouselContent(){
		try {
			this.nav();
			carousel = this.carouselService.carousel(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "carouselContent";
	}
	
	/**
	 * 获取代表性论文
	 */
	public String thesisContent(){
		this.nav();
		thesis = thesisService.getById(id);
		return "thesisContent";
	}
	/*public String lunwenContent(){
		try {
			this.nav();
			teacher = this.teacherService.GetById(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return "lunwenContent";
	}*/
	/**
	 * 跳转到研究院简介的方法
	 * @return
	 */
	public String profileto(){
		try {
			this.nav();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "profileto";
	}

	/**
	 * 获取研究院简介内容的方法
	 * @return
	 */
	public String getContent(){
		try {
			profile = this.profileService.profile(id);
			this.nav();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "profileto";
	}
	
	
	/**
	 * 师资力量页面
	 * @return
	 */
	public String teacher(){
		try {
			this.nav();
			if(id==0){
				teaId = groupList.get(0).getId();
				teaList = teacherService.getByTeaTeam_id(teaId);
			}else{
				teaList = teacherService.getByTeaTeam_id(id);
			}
			for(int i = 0;i<teaList.size();i++){
			 researchList =	researchService.getListByTeaId(teaList.get(i).getId());
			 if(researchList.size() != 0){
				 teaList.get(i).setResult(1);
			 }
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return  "teacher";
	}
	
	/**
	 * 教师详细信息
	 */
	public String teaContent(){
		try {
			this.nav();
			teacher = teacherService.GetById(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "teaContent";
	}
	/**
	 * 教师实验室
	 */
	public String research(){
		try {
			this.nav();
			if(id==0){
				research = researchService.GetById(resId);
				teacher = teacherService.GetById(research.getTeaId());
				researchList =  researchService.getListByTeaId(teacher.getId());
			}else{
				teacher = teacherService.GetById(id);
				researchList = researchService.getListByTeaId(id);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "research";
	}
	/**
	 * 获取导航栏第三栏界面的方法
	 */
	public String navThreeto(){
		try {
			this.nav();
			pagination2 = this.articleService.artTitlePg(pageno, pageSize, nav_oneName);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navThreeto";
	}
	/**
	 * 获取导航栏第四栏界面的方法
	 * @return
	 */
	public String navFourto(){
		try {
			pagination2 = this.articleService.artTitlePg(pageno, pageSize, nav_oneName);
			this.nav();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navFourto";
	}
	/**
	 * 获取导航栏第五栏界面的方法
	 * @return
	 */
	public String navFiveto(){
		try {
			pagination2 = this.articleService.artTitlePg(pageno, pageSize, nav_oneName);
			this.nav();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navFiveto";
	}
	/**
	 * 通过nav_oneName获取内标题列表 导航栏第三栏
	 */
	public String getnavContent(){
		try {
			Article article = new Article();
			article.setNav_oneName(nav_oneName);
			nav_oneName_id = nav_oneName;
			pagination2 = this.articleService.artTitlePg(pageno, pageSize, nav_oneName_id);
			this.nav();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navThreeto";
	}
	/**
	 * 通过nav_oneName获取内标题列表 导航栏第四栏
	 */
	public String getnavContentFour(){
		try {
			Article article = new Article();
			article.setNav_oneName(nav_oneName);
			nav_oneName_id = nav_oneName;
			pagination2 = this.articleService.artTitlePg(pageno, pageSize, nav_oneName_id);
			this.nav();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navFourto";
	}
	/**
	 * 通过nav_oneName获取内标题列表 导航栏第五栏
	 */
	public String getnavContentFive(){
		try {
			Article article = new Article();
			article.setNav_oneName(nav_oneName);
			nav_oneName_id = nav_oneName;
			pagination2 = this.articleService.artTitlePg(pageno, pageSize, nav_oneName_id);
			this.nav();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navFiveto";
	}
	/**
	 * 通过标题 获取内容  导航栏第三栏
	 */
	public String navThreeContent(){
		try {
			this.nav();
			article = this.articleService.article(id);
			int id = article.getNav_oneName();
			nav = this.navService.nav(id);
			String oneName = "";
			oneName = oneName + nav.getOneName();
			article.setOneName(oneName);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navThreeContent";
	}
	/**
	 * 通过标题 获取内容  导航栏第四栏
	 */
	public String navFourContent(){
		try {
			this.nav();
			article = this.articleService.article(id);
			int id = article.getNav_oneName();
			nav = this.navService.nav(id);
			String oneName = "";
			oneName = oneName + nav.getOneName();
			article.setOneName(oneName);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navFourContent";
	}
	/**
	 * 通过标题 获取内容  导航栏第五栏
	 */
	public String navFiveContent(){
		try {
			this.nav();
			article = this.articleService.article(id);
			int id = article.getNav_oneName();
			nav = this.navService.nav(id);
			String oneName = "";
			oneName = oneName + nav.getOneName();
			article.setOneName(oneName);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "navFiveContent";
	}
	
	/**
	 * 学习天地
	 */
	public String studyto(){
		try {
			this.nav();
			studyoneList = this.studyoneService.studyoneList();
			pagination6 = this.studyService.studyPg(pageno, pageSize);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "studyto";
	}
	/**
	 * 学习天地显示二级标题
	 */
	public String studytoto(){
		try {
			this.nav();
			studyone = this.studyoneService.studyone(id);
			int studyOneName_id = id;
			studytwoList = this.studytwoService.studytwoListByid(studyOneName_id);
			pagination6 = this.studyService.studyByonePg(pageno, pageSize, studyOneName_id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "studytoto";
	}
	public String studytototo(){
		try {
			studyList = this.studyService.studyListByTwo(studyTwoName_id);
			studytwo = this.studytwoService.studytwo(studyTwoName_id);
			int studyOneName_id = studytwo.getStudyOneName_id();
			this.nav();
			studyoneList = this.studyoneService.studyoneList();
			studyone = this.studyoneService.studyone(studyOneName_id);
			studytwoList = this.studytwoService.studytwoListByid(studyOneName_id);
			pagination6 = this.studyService.studyBytwoPg(pageno, pageSize, studyTwoName_id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "studytototo";
	}
	/**
	 * 获取学习天地内容
	 */
	public String studyContent(){
		try {
			this.nav();
			study = this.studyService.study(id);
			int studyOneName_id = study.getStudyOneName_id();
			studyone = this.studyoneService.studyone(studyOneName_id);
			String studyOneName = "";
			studyOneName = studyOneName + studyone.getStudyOneName();
			study.setStudyOneName(studyOneName);
			int studuTwoName_id = study.getStudyTwoName_id();
			if(studuTwoName_id !=0){
			studytwo = this.studytwoService.studytwo(studuTwoName_id);
			String studyTwoName = "";
			studyTwoName = studyTwoName + studytwo.getStudyTwoName();
			study.setStudyTwoName(studyTwoName);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "studyContent";
	}
	/**
	 * get set
	 * @return
	 */
	
	public IProfileService getProfileService() {
		return profileService;
	}
	
	public List<Study> getStudyList() {
		return studyList;
	}

	public void setStudyList(List<Study> studyList) {
		this.studyList = studyList;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public int getStudyTwoName_id() {
		return studyTwoName_id;
	}

	public void setStudyTwoName_id(int studyTwoName_id) {
		this.studyTwoName_id = studyTwoName_id;
	}

	public String getStudyOneName() {
		return studyOneName;
	}

	public void setStudyOneName(String studyOneName) {
		this.studyOneName = studyOneName;
	}

	public List<Studytwo> getStudytwoList() {
		return studytwoList;
	}

	public void setStudytwoList(List<Studytwo> studytwoList) {
		this.studytwoList = studytwoList;
	}

	public Studyone getStudyone() {
		return studyone;
	}

	public void setStudyone(Studyone studyone) {
		this.studyone = studyone;
	}

	public Pagination<Study> getPagination6() {
		return pagination6;
	}

	public void setPagination6(Pagination<Study> pagination6) {
		this.pagination6 = pagination6;
	}

	public IStudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(IStudyService studyService) {
		this.studyService = studyService;
	}

	public IStudyoneService getStudyoneService() {
		return studyoneService;
	}

	public void setStudyoneService(IStudyoneService studyoneService) {
		this.studyoneService = studyoneService;
	}

	public IStudytwoService getStudytwoService() {
		return studytwoService;
	}

	public void setStudytwoService(IStudytwoService studytwoService) {
		this.studytwoService = studytwoService;
	}

	public List<Studyone> getStudyoneList() {
		return studyoneList;
	}

	public void setStudyoneList(List<Studyone> studyoneList) {
		this.studyoneList = studyoneList;
	}

	public ITeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public ITeachergroupService getTeachergroupService() {
		return teachergroupService;
	}
	public void setTeachergroupService(ITeachergroupService teachergroupService) {
		this.teachergroupService = teachergroupService;
	}
	public IResearchService getResearchService() {
		return researchService;
	}
	public void setResearchService(IResearchService researchService) {
		this.researchService = researchService;
	}
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

	public List<Profile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public List<Teachergroup> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Teachergroup> groupList) {
		this.groupList = groupList;
	}
	public int getTeaTeam_id() {
		return teaTeam_id;
	}
	public void setTeaTeam_id(int teaTeam_id) {
		this.teaTeam_id = teaTeam_id;
	}
	public List<Teacher> getTeaList() {
		return teaList;
	}
	public void setTeaList(List<Teacher> teaList) {
		this.teaList = teaList;
	}
	public INavService getNavService() {
		return navService;
	}
	public void setNavService(INavService navService) {
		this.navService = navService;
	}
	
	public List<Nav> getNavthreeList() {
		return navthreeList;
	}
	public void setNavthreeList(List<Nav> navthreeList) {
		this.navthreeList = navthreeList;
	}
	public String getNavName() {
		return navName;
	}
	public void setNavName(String navName) {
		this.navName = navName;
	}
	public IArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Pagination<Article> getPagination2() {
		return pagination2;
	}
	public void setPagination1(Pagination<Article> pagination2) {
		this.pagination2 = pagination2;
	}
	
	public int getNav_oneName() {
		return nav_oneName;
	}
	public void setNav_oneName(int nav_oneName) {
		this.nav_oneName = nav_oneName;
	}
	
	public int getNav_oneName_id() {
		return nav_oneName_id;
	}
	public void setNav_oneName_id(int nav_oneName_id) {
		this.nav_oneName_id = nav_oneName_id;
	}
	public void setPagination2(Pagination<Article> pagination2) {
		this.pagination2 = pagination2;
	}
	public List<Nav> getNavfourList() {
		return navfourList;
	}
	public void setNavfourList(List<Nav> navfourList) {
		this.navfourList = navfourList;
	}
	public List<Nav> getNavfiveList() {
		return navfiveList;
	}
	public void setNavfiveList(List<Nav> navfiveList) {
		this.navfiveList = navfiveList;
	}
	public Nav getNav() {
		return nav;
	}
	public void setNav(Nav nav) {
		this.nav = nav;
	}
	public int getTeaId() {
		return teaId;
	}

	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}
	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Research getResearch() {
		return research;
	}

	public void setResearch(Research research) {
		this.research = research;
	}

	public List<Research> getResearchList() {
		return researchList;
	}

	public void setResearchList(List<Research> researchList) {
		this.researchList = researchList;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getOneName() {
		return oneName;
	}

	public void setOneName(String oneName) {
		this.oneName = oneName;
	}

	public List<Nav> getNavList() {
		return navList;
	}

	public void setNavList(List<Nav> navList) {
		this.navList = navList;
	}

	public int getPageno1() {
		return pageno1;
	}

	public void setPageno1(int pageno1) {
		this.pageno1 = pageno1;
	}

	public int getPageSize1() {
		return pageSize1;
	}

	public void setPageSize1(int pageSize1) {
		this.pageSize1 = pageSize1;
	}

	public Pagination<Article> getPagination3() {
		return pagination3;
	}

	public void setPagination3(Pagination<Article> pagination3) {
		this.pagination3 = pagination3;
	}

	public int getPageno2() {
		return pageno2;
	}

	public void setPageno2(int pageno2) {
		this.pageno2 = pageno2;
	}

	public int getPageSize2() {
		return pageSize2;
	}

	public void setPageSize2(int pageSize2) {
		this.pageSize2 = pageSize2;
	}

	public Pagination<Article> getPagination4() {
		return pagination4;
	}

	public void setPagination4(Pagination<Article> pagination4) {
		this.pagination4 = pagination4;
	}

	public ICarouselService getCarouselService() {
		return carouselService;
	}

	public void setCarouselService(ICarouselService carouselService) {
		this.carouselService = carouselService;
	}

	public String getCarouselName() {
		return carouselName;
	}

	public void setCarouselName(String carouselName) {
		this.carouselName = carouselName;
	}

	public String getCarouselPhoto() {
		return carouselPhoto;
	}

	public void setCarouselPhoto(String carouselPhoto) {
		this.carouselPhoto = carouselPhoto;
	}

	public String getCarouselTime() {
		return carouselTime;
	}

	public void setCarouselTime(String carouselTime) {
		this.carouselTime = carouselTime;
	}

	public String getCarouselContent() {
		return carouselContent;
	}

	public void setCarouselContent(String carouselContent) {
		this.carouselContent = carouselContent;
	}

	public int getCarouselNum() {
		return carouselNum;
	}

	public void setCarouselNum(int carouselNum) {
		this.carouselNum = carouselNum;
	}

	public int getPageno3() {
		return pageno3;
	}

	public void setPageno3(int pageno3) {
		this.pageno3 = pageno3;
	}

	public int getPageSize3() {
		return pageSize3;
	}

	public void setPageSize3(int pageSize3) {
		this.pageSize3 = pageSize3;
	}

	public Pagination<Carousel> getPagination5() {
		return pagination5;
	}

	public void setPagination5(Pagination<Carousel> pagination5) {
		this.pagination5 = pagination5;
	}

	public Carousel getCarousel() {
		return carousel;
	}

	public void setCarousel(Carousel carousel) {
		this.carousel = carousel;
	}

	public Studytwo getStudytwo() {
		return studytwo;
	}

	public void setStudytwo(Studytwo studytwo) {
		this.studytwo = studytwo;
	}

	public List<Education> getEduList() {
		return eduList;
	}

	public void setEduList(List<Education> eduList) {
		this.eduList = eduList;
	}

	public IEducationService getEducationService() {
		return educationService;
	}

	public void setEducationService(IEducationService educationService) {
		this.educationService = educationService;
	}

	public IThesisService getThesisService() {
		return thesisService;
	}

	public void setThesisService(IThesisService thesisService) {
		this.thesisService = thesisService;
	}
	public List<Thesis> getThesisList() {
		return thesisList;
	}

	public void setThesisList(List<Thesis> thesisList) {
		this.thesisList = thesisList;
	}

	public int getPageno11() {
		return pageno11;
	}

	public void setPageno11(int pageno11) {
		this.pageno11 = pageno11;
	}

	public int getPageSize11() {
		return pageSize11;
	}

	public void setPageSize11(int pageSize11) {
		this.pageSize11 = pageSize11;
	}

	public Pagination<Article> getPagination31() {
		return pagination31;
	}

	public void setPagination31(Pagination<Article> pagination31) {
		this.pagination31 = pagination31;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Thesis getThesis() {
		return thesis;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}

	public List<Nav> getNavTop() {
		return navTop;
	}

	public void setNavTop(List<Nav> navTop) {
		this.navTop = navTop;
	}

	public List<Article> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<Article> newsList) {
		this.newsList = newsList;
	}

	public List<Carousel> getCarouselList() {
		return carouselList;
	}

	public void setCarouselList(List<Carousel> carouselList) {
		this.carouselList = carouselList;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public List<Article> getLectureList() {
		return lectureList;
	}

	public void setLectureList(List<Article> lectureList) {
		this.lectureList = lectureList;
	}
}
