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
        agency_code blank: false, unique: true
        correspondent_id blank: true
        description blank: false
        disabled blank: false
        distance blank: true
        id blank: false, unique: true
        payment_method_id blank: false
        phone blank: true
        site_id blank: false
        terminal blank: false

    }
}
