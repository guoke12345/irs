package com.framework.core.web.taglib;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.commons.lang.math.NumberUtils;
import com.framework.core.page.Pagination;

/**
 * 分页标签。 <BR>
 * <UL>
 * <LI>对 scope 中的集合进行分页处理。</LI>
 * </UL>
 * <BR>
 * @author gaof
 * @version 1.0
 * @since 1.0
 */
public class PageTag extends BodyTagSupport {

	//public static final String PAGE_NUMBER = "com.framework.core.taglib.page.currPage";
	public static final String PAGE_NUMBER = "pageno";
    /**
     * 默认每页显示记录数
     */
	public static int DEFAULT_PAGE_SIZE = 20;

	/**
	 * 保存输出
	 */
	private StringBuffer output;

	/**
	 * 当前 form 要提交的url
	 */
	private String url = null;

	/**
	 * 要遍历的集合在 scope 中的别名
	 */
	private String name = "pagination";

	/**
	 * collection 对象的引用
	 */ 
	private Object obj = null;
	/**
	 * 总页数
	 */
	private int pages = 0;

	/**
	 * 用户指定的要显示的页号
	 */
	private String currPage = null;

	/**
	 * 当前要显示的页号
	 */ 
	private int currPageValue = 0;

	/**
	 * 默认查询Form的id
	 */
    private String queryForm = "queryForm";
    
    /**
     * 是否启用Ajax查询方式
     */
    private boolean isAjax =false;
    
    
	/**
	 * 覆盖父类 doStartTag 方法。 <br>
	 * <ul>
	 * <li>在用户对集合进行遍历输出之前拦截集合， 对集合进行分页处理， 用处理后的集合代替原有集合，并获取分页信息。</li>
	 * </ul>
	 * 
	 * @return 返回 EVAL_BODY_BUFFERED 表示计算标签体
	 * @throws JspException
	 *             参数无效时抛出异常
	 */
	public int doStartTag() throws JspException {

		// 实例化 output ，以保存标签体内容。
		output = new StringBuffer();
		// 如果 action 没输入
		if (null == url || (!(url instanceof String))
				|| "".equals(url.trim())) {
			// 抛出异常：action 属性应为当前 form 所要提交到的 action 的非空的字符串 URL。
			throw new JspException("url不能为空!");
		}

		// 如果 collection 在 scope 中不存在
		if (0 == pageContext.getAttributesScope(name)) {
			// 抛出异常：pagination 在 scope 范围内不存在
			throw new JspException("对象不存在!");
		}
		// 从 scope 中取得 pagination 的引用
		obj = pageContext.findAttribute(name);
		// 查找不到则表示 pagination 为空
		if (obj == null) {
			// 将 collection 设为空 ArrayList
			throw new JspException("Pagination对象不能为空！");
		}
		// 如果 collectionObj 是 Collection
		if (!(obj instanceof Pagination)) {
			// 取得记录总数
			throw new JspException("实例必须为Pagination类型的对象！");
		}
		Pagination pagination = (Pagination)obj;
		// 从 request 中取得当前要显示的页码
		currPage = pageContext.getRequest().getParameter(PAGE_NUMBER);
		// 没有输入默认为 1
		// 输入 currPage 则 currPageValue 等于 currPage
		// 数据类型不同则 currPageValue 默认为 1
		currPageValue = NumberUtils.toInt(currPage, 1);
		// 计算总页数
		pages = pagination.getMaxPages();//
		// 页号越界处理
		// 最小为 1
		if (currPageValue <= 0) {
			currPageValue = 1;
		}
		// 不可大于总页数
		if (currPageValue > pages) {
			currPageValue = pages;
		}
	    // 计算标签体
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * 覆盖父类 doEndTag 方法。 <br>
	 * <ul>
	 * <li>在用户对处理后的集合进行遍历输出之后， 在集合内容下方 显示分页信息。</li>
	 * </ul>
	 * 
	 * @return 返回 EVAL_PAGE 表示计算页面其他部分
	 */
	public int doEndTag() {

		// 取得 BodyContent
		//BodyContent bodyContent = getBodyContent();

		// 如果 BodyContent 不为 null
		//if (bodyContent != null) {
			// 将标签体的内容追加到 output 中
			//  output.append(bodyContent.getString());
			// 追加分页信息			
			  output.append("<br/>")
					.append("<table border='0' cellspacing='0' cellpadding='0' width='90%' align='center'>")
					.append("<tr>")
					.append("<td align='right' valign='top'>");

			// 如果总页数小于 1 ，第一页无连接
			if (pages > 1 && currPageValue > 1) {
				//非Ajax方式
				if(isAjax){
					output.append("<a href=\"javascript:{document.getElementById('")
					      .append(PAGE_NUMBER)
					      .append("').value='1';getPage();}\">")
					      .append("首页</a>&nbsp;");
					
				}else{
					output.append("<a href=\"javascript:{document.getElementById('"+queryForm+"').action='")
						  .append(url)
				          .append("';document.getElementById('")
				          .append(PAGE_NUMBER)
				          .append("').value='1';document.getElementById('"+queryForm+"').submit();}\">")
				          .append("首页</a>&nbsp;");
				}
				
			} else {
				output.append("首页&nbsp;");
			}

			// 如果当前页小于 1 ，前一页无连接
			if (currPageValue > 1) {
				 if(isAjax){
				  output.append("<a href=\"javascript:{document.getElementById('")
						.append(PAGE_NUMBER)
						.append("').value='")
						.append(currPageValue - 1)
						.append("';getPage();}\">")
						.append("上一页</a>&nbsp;");
				 }else{
				  output.append("<a href=\"javascript:{document.getElementById('"+queryForm+"').action='")
						.append(url)
						.append("';document.getElementById('")
						.append(PAGE_NUMBER)
						.append("').value='")
						.append(currPageValue - 1)
						.append("';document.getElementById('"+queryForm+"').submit();}\">")
						.append("上一页</a>&nbsp;");
				 }
			} else {
				  output.append("上一页&nbsp;");
			}
			      output.append("第")
			            .append(currPageValue)
			            .append("页/共")
				        .append(pages)
				        .append("页&nbsp;");

			// 总页数大于当前页数时后一页有连接
			if (pages > currPageValue) {
				if(isAjax){
				  output.append("<a href=\"javascript:{document.getElementById('")
						.append(PAGE_NUMBER)
						.append("').value='")
						.append(currPageValue + 1)
						.append("';getPage();}\">")
						.append("下一页</a>&nbsp;");
				}else{
				  output.append("<a href=\"javascript:{document.getElementById('"+queryForm+"').action='")
						.append(url)
						.append("';document.getElementById('")
						.append(PAGE_NUMBER)
						.append("').value='")
						.append(currPageValue + 1)
						.append("';document.getElementById('"+queryForm+"').submit();}\">")
						.append("下一页</a>&nbsp;");
				}
			} else {
				  output.append("下一页&nbsp;");
			}

			// 如果总页数小于 1 ，最后一页无连接
			if (pages > 1 && pages > currPageValue) {
				if(isAjax){
				 output.append("<a href=\"javascript:{document.getElementById('")
						.append(PAGE_NUMBER)
						.append("').value='")
						.append(pages)
						.append("';getPage();}\">")
						.append("末页</a> ");
				}else{
				  output.append("<a href=\"javascript:{document.getElementById('"+queryForm+"').action='")
						.append(url)
						.append("';document.getElementById('")
						.append(PAGE_NUMBER)
						.append("').value='")
						.append(pages)
						.append("';document.getElementById('"+queryForm+"').submit();}\">")
						.append("末页</a> ");
				}
			} else {
				  output.append("末页   ");
			}
			//跳转
				output.append("<input size='3' type='text' name='txtTiao'  id='txtTiao'>");
				output.append("<input type='button' value='跳转' onclick=\"javascript:{" +
						"var pageno=document.getElementById('txtTiao').value;"+
						"var pages=document.getElementById('txtPageno').value;"+
						"if(pageno > pages){" +
							"pageno = pages;}"+
						"document.getElementById('" + PAGE_NUMBER + "').value=pageno;")
						.append("document.getElementById('"+queryForm+"').action='")
						.append(url)
						.append("';document.getElementById('"+queryForm+"').submit();}\">")
						.append("</td></tr></table>");
				//pages总页数
				output.append("<input type='hidden' name='txtPageno' id='txtPageno' value='")
				.append(pages + "'>");
			      output.append("<input type='hidden' name='")
			            .append(PAGE_NUMBER+"'")
			            .append(" id='"+PAGE_NUMBER+"'")
			            .append(" value='")
			            .append(currPageValue)
			            .append("'>");

			// 输出全部内容
			try {
				pageContext.getOut().println(output.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	//	}

		// 计算页面其他部分
		return EVAL_PAGE;
	}
	//////////////////////////////////////
	//////getter/setter方法
	//////////////////////////////////////
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getQueryForm() {
		return queryForm;
	}

	public void setQueryForm(String queryForm) {
		this.queryForm = queryForm;
	}

	public boolean isAjax() {
		return isAjax;
	}

	public void setIsAjax(boolean isAjax) {
		this.isAjax = isAjax;
	}

	
	private static final long serialVersionUID = 1L;

}