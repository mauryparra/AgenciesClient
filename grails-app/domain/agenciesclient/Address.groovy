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
        address_line blank: false
        city blank: false
        country blank: false
        location blank: false
        other_info blank: true
        state blank: false
        zip_code blank: false
    }
}
