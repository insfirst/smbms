<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="get" >
					<!-- <input name="method" value="query" class="input-text" type="hidden"> -->
					 <span>用户名：</span>
					 <input name="userName" class="input-text"	type="text" value="${userName }">
					 
					 <span>用户角色：</span>
					 <select name="userRole">
						<c:if test="${roleList != null }">
						   <option value="0">--请选择--</option>
						   <c:forEach var="role" items="${roleList}">
						   		<option <c:if test="${role.id == userRole }">selected="selected"</c:if>
						   		value="${role.id}">${role.roleName}</option>
						   </c:forEach>
						</c:if>
	        		</select>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/user/add" >添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">出生日期</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户角色</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="user" items="${pager.list }" varStatus="status">
					<tr>
						<td>
						<input type="hidden" name="id" value="${user.id }"/>
						<span>${user.userCode }</span>
						</td>
						<td>
						<span>${user.userName }</span>
						</td>
						<td>
							<span>
								<c:if test="${user.gender==1}">男</c:if>
								<c:if test="${user.gender==2}">女</c:if>
							</span>
						</td>
						<td>
						<span>
							<fmt:formatDate value="${user.birthday}"/>
						</span>
						</td>
						<td>
						<span>${user.phone}</span>
						</td>
						<td>
							<span>${user.userRoleName}</span>
						</td>
						<td>
						<span><a class="viewUser" href="${pageContext.request.contextPath }/user/view/${user.id }"><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyUser" href="${pageContext.request.contextPath }/user/modify/${user.id }"><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a class="deleteUser" href="${pageContext.request.contextPath }/user/remove/${user.id }"><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="totalPageCount" value="${pager.totalPages}"/>
		  	 <c:import url="../rollpage.jsp">
	          	<c:param name="totalCount" value="${pager.totalCount}"/>
	          	<c:param name="currentPageNo" value="${pager.pageIndex}"/>
	          	<c:param name="totalPageCount" value="${pager.totalPages}"/>
          	</c:import>
          	<div class="providerView">
            <p><strong>用户编号：</strong><span>${user.userCode }</span></p>
            <p><strong>用户名称：</strong><span>${user.userName }</span></p>
            <p><strong>用户性别：</strong><span>${user.gender}</span></p>
            <p><strong>出生日期：</strong><span>${user.birthday }</span></p>
            <p><strong>用户电话：</strong><span>${user.phone }</span></p>
            <p><strong>用户地址：</strong><span>${user.address }</span></p>
            <p><strong>用户角色：</strong><span>${user.userRoleName}</span></p>
        </div>
        </div>
    </section>
     

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="../common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/userlist.js"></script>
<<script type="text/javascript">

	$(function(){
		$(".providerTable tr:not('.firstTr')").on("mouseover",function(){
			var id=$(this).find("td").first().find("input").val();
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/user/view/"+id,
				dataType:"json",
				success:function(result){
					var user=result.user;
					$(".providerView p span").eq(0).text(user.userCode);
					$(".providerView p span").eq(1).text(user.userName);
					$(".providerView p span").eq(2).text(user.gender==1?"男":"女");
					$(".providerView p span").eq(3).text(user.birthday);
					$(".providerView p span").eq(4).text(user.phone);
					$(".providerView p span").eq(5).text(user.address);
					$(".providerView p span").eq(6).text(user.userRoleName);
				}
			})
		})
	})


</script>
