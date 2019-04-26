<g:each var="agency" in="${agencies}">
    <li>${agency.description} (${agency.address.address_line})</li>
</g:each>