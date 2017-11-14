package community

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TravelController {

    TravelService travelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond travelService.list(params), model:[travelCount: travelService.count()]
    }

    def show(Long id) {
        respond travelService.get(id)
    }

    def create() {
        respond new Travel(params)
    }

    def save(Travel travel) {
        if (travel == null) {
            notFound()
            return
        }

        try {
            travelService.save(travel)
        } catch (ValidationException e) {
            respond travel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'travel.label', default: 'Travel'), travel.id])
                redirect travel
            }
            '*' { respond travel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond travelService.get(id)
    }

    def update(Travel travel) {
        if (travel == null) {
            notFound()
            return
        }

        try {
            travelService.save(travel)
        } catch (ValidationException e) {
            respond travel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'travel.label', default: 'Travel'), travel.id])
                redirect travel
            }
            '*'{ respond travel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        travelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'travel.label', default: 'Travel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'travel.label', default: 'Travel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
