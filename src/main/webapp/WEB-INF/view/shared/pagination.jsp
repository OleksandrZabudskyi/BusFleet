<%@ include file="tags.jsp" %>
<ul class="pagination">
    <c:if test="${currentPage != 1}">
        <li><a href="?page=${currentPage - 1}">Previous</a></li>
    </c:if>
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="active"><a href="">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="?page=${i}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt numberOfPages}">
        <li><a href="?page=${currentPage + 1}">Next</a></li>
    </c:if>
</ul>
