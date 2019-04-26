package agenciesclient

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AgencyController {

    AgencyService agencyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond agencyService.list(params), model:[agencyCount: agencyService.count()]
    }

    def show(Long id) {
        respond agencyService.get(id)
    }

    def create() {
        respond new Agency(params)
    }

    def save(Agency agency) {
        if (agency == null) {
            notFound()
            return
        }

        try {
            agencyService.save(agency)
        } catch (ValidationException e) {
            respond agency.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'agency.label', default: 'Agency'), agency.id])
                redirect agency
            }
            '*' { respond agency, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond agencyService.get(id)
    }

    def update(Agency agency) {
        if (agency == null) {
            notFound()
            return
        }

        try {
            agencyService.save(agency)
        } catch (ValidationException e) {
            respond agency.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'agency.label', default: 'Agency'), agency.id])
                redirect agency
            }
            '*'{ respond agency, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        agencyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'agency.label', default: 'Agency'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'agency.label', default: 'Agency'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
