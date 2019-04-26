package agenciesclient

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AddressController {

    AddressService addressService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond addressService.list(params), model:[addressCount: addressService.count()]
    }

    def show(Long id) {
        respond addressService.get(id)
    }

    def create() {
        respond new Address(params)
    }

    def save(Address address) {
        if (address == null) {
            notFound()
            return
        }

        try {
            addressService.save(address)
        } catch (ValidationException e) {
            respond address.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'address.label', default: 'Address'), address.id])
                redirect address
            }
            '*' { respond address, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond addressService.get(id)
    }

    def update(Address address) {
        if (address == null) {
            notFound()
            return
        }

        try {
            addressService.save(address)
        } catch (ValidationException e) {
            respond address.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'address.label', default: 'Address'), address.id])
                redirect address
            }
            '*'{ respond address, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        addressService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'address.label', default: 'Address'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'address.label', default: 'Address'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
