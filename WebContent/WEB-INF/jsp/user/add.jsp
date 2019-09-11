<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form id="userForm" name="userForm" method="post" enctype="multipart/form-data" >
				<!-- <input type="hidden" name="method" value="add"> -->
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userCode">用户编码：</label>
                    <input type="text" name="userCode" id="userCode" value=""/> 
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="userName" >用户名称：</label>
                    <input type="text" name="userName" id="userName" value=""/> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="userPassword" >用户密码：</label>
                    <input type="password" name="userPassword" id="userPassword" value=""/> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="password" name="ruserPassword" id="ruserPassword" value=""/> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="gender">用户性别：</label>
					<select name="gender" id="gender">
					    <option value="1" selected="selected">男</option>
					    <option value="2">女</option>
					 </select>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" Class="Wdate" id="birthday" name="birthday" 
					readonly="readonly" onclick="WdatePicker();"/>
					<font color="red"></font>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value=""/> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                   <input name="address" id="address"  value=""/>
                </div>
                <div>
                    <label for="userRole">用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<select name="userRole" id="userRole">
						<c:if test="${roleList != null }">
						   <option value="0">--请选择--</option>
						   <c:forEach var="role" items="${roleList}">
						   		<option <c:if test="${role.id == userRole }">selected="selected"</c:if>
						   		value="${role.id}">${role.roleName}</option>
						   </c:forEach>
						</c:if>
	        		</select>
	        		<font color="red"></font>
                </div>
                 <div>
                    <label for="idFile">证件照：</label>
                   <input name="idFile" id="idFile" type="file">
                   <font color="red">${fileError}</font>
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
        </div>
</div>
</section>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
$(function(){
	$("#userCode").on("keyup",function(){
		var userCode=$(this).val();
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/user/userCodeExists?userCode="+ userCode,
			success: function(result){
				if(result){
					$("#userCode").next().html("该用户编码已经被使用");
				} else{
					$("#userCode").next().html("该用户编码可用");
				}
			}
		})
		
		
	})
	$("#userCode").blur(function(){
		var userCode=$(this).val();
		if(userCode==null||userCode==""){
			$("#userCode").next().html("不能为空！");
		}
	})
	
})
</script>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/useradd.js"></script> --%>
