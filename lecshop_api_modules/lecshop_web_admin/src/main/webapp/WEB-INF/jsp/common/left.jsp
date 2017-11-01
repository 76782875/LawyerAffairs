<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<aside>
    <div id="sidebar" class="nav-collapse ">
        <ul class="sidebar-menu" id="nav-accordion">
            <c:forEach items="${adminMenu}" var="firstMenu">
                <c:if test="${firstMenu.authorityId==firstMenus}">
                    <c:if test="${firstMenu.childMenu!=null}">
                        <c:forEach items="${firstMenu.childMenu}" var="secondMenu">
                            <li class="sub-menu"><a href="javascript:;" <c:if test="${secondMenu.authorityId==secondMenus}"> class="active" </c:if> > <span>${secondMenu.name}</span></a>
                                <ul class="sub">
                                    <c:if test="${secondMenu.childMenu!=null}">
                                        <c:forEach items="${secondMenu.childMenu}" var="thirdMenu">
                                            <li <c:if test="${thirdMenu.authorityId==thirdMenus}"> class="active"</c:if> >
                                                <a href="${thirdMenu.url }?firstMenus=${firstMenu.authorityId}&secondMenus=${secondMenu.authorityId}&thirdMenus=${thirdMenu.authorityId}">${thirdMenu.name}</a>
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </li>
                        </c:forEach>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</aside>

