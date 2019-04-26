<ul class="list-group"">
<g:each var="agency" in="${agencies}">
    <li class="list-group-item">${agency.description} (${agency.address.address_line})
        <button class="btn btn-outline-info btn-sm" value="${agency.agency_code}" onclick="saveAgency(this)">Like</button>
        <button class="btn btn-outline-danger btn-sm" value="${agency.agency_code}" onclick="deleteAgency(this)">Unlike</button></li>
</g:each>
</ul>