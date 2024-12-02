<#macro pageable url page>
    <#assign
    countPage = page.getTotalPages() - 1
    numCurrentPage = page.getNumber()
    />

    <nav>
        <ul class="pagination justify-content-center">
            <#if numCurrentPage=0>
                <li class="page-item disabled">
                    <span class="page-link">Предыдущая</span>
                </li>
            <#else>
                <li class="page-item"><a class="page-link" href="${url}page=${numCurrentPage-1}">Предыдущая</a></li>
            </#if>

            <#if numCurrentPage=countPage || countPage=-1>
                <li class="page-item disabled">
                    <span class="page-link">Следующая</span>
                </li>
            <#else>
                <li class="page-item"><a class="page-link" href="${url}page=${numCurrentPage+1}">Следующая</a></li>
            </#if>
        </ul>
    </nav>
</#macro>