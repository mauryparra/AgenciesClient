package agenciesclient

import grails.rest.Resource

@Resource(uri='/address')
class Address {

    String address_line
    String city
    String country
    String location
    String other_info
    String state
    String zip_code

    static belongsTo = [agency: Agency]

    static constraints = {
        address_line nullable: false
        city nullable: false
        country nullable: false
        location nullable: false
        other_info nullable: true
        state nullable: false
        zip_code nullable: true
    }
}
