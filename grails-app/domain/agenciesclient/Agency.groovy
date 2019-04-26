package agenciesclient

import grails.rest.Resource


@Resource(uri='/agency')
class Agency {

    static hasOne = [address: Address]
    String agency_code
    String correspondent_id
    String description
    String disabled
    String distance
    String id
    String payment_method_id
    String phone
    String site_id
    String terminal


    static constraints = {
        address nullable: false
        agency_code nullable: false, unique: true
        correspondent_id nullable: true
        description nullable: true
        disabled nullable: true
        distance nullable: true
        id blank: false, unique: true
        payment_method_id nullable: true
        phone nullable: true
        site_id nullable: true
        terminal nullable: true

    }
}
