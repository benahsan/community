package community

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FamilyController {

    def exportService

    FamilyService familyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond familyService.list(params), model:[familyCount: familyService.count()]
    }

    def arab(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Family.findAll("from Family as f where f.country.region=?", ["العرب"] , params), model:[familyCount: familyService.count()]
    }

    def west(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Family.findAll("from Family as f where f.country.region=?", ["الغرب"] , params), model:[familyCount: familyService.count()]
    }

    def asia(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Family.findAll("from Family as f where f.country.region=?", ["شرق آسيا"] , params), model:[familyCount: familyService.count()]
    }

    def exportExcel() {
        params.exportFormat = "excel"
        params.extension = "xls"

        if(params.exportFormat && params.exportFormat != "html") {

            def families = Family.list(params)

            def results = []
            families.each { f ->
                def map = [:]
                map.put("fileNumber",f.fileNumber)
                map.put("headOfFamily",f.headOfFamily)
                map.put("country",f.country)
                map.put("contact",f.contact)
                map.put("personResponsible",f.personResponsible)
                map.put("contactPersonResponsible",f.contactPersonResponsible)
                map.put("address",f.address)
                map.put("member",f.member)
                map.put("notes",f.notes)
                results.add(map)
            }            

			response.contentType = grailsApplication.config.grails.mime.types[params.exportFormat]
			response.setHeader("Content-disposition", "attachment; filename=families.xls")

            List fields = [ "fileNumber", "headOfFamily", "country", "contact", "personResponsible", "contactPersonResponsible", "address", "member", "notes"]

            Map labels = ["fileNumber": "رقم الملف", "headOfFamily": "رب الأسرة", "country": "البلاد" , "contact": "الاتصال", "personResponsible":"المسؤول عن الأسرة" , "contactPersonResponsible":"اتصال المسؤول" , "address":"العنوان" , "member":"أفراد الأسرة" , "notes":"ملاحظات" ]

            Map formatters = [:]
            Map parameters = [:]

            exportService.export(params.exportFormat, response.outputStream, results, fields, labels, formatters, parameters)
        }
    }

    def country(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond familyService.list(params), model:[familyCount: familyService.count()]
    }

    def contact(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond familyService.list(params), model:[familyCount: familyService.count()]
    }

    def responsible(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond familyService.list(params), model:[familyCount: familyService.count()]
    }

    def address(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond familyService.list(params), model:[familyCount: familyService.count()]
    }

    def members(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond familyService.list(params), model:[familyCount: familyService.count()]
    }

    def notes(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond familyService.list(params), model:[familyCount: familyService.count()]
    }

    def show(Long id) {
        respond familyService.get(id)
    }

    def create() {
        respond new Family(params)
    }

    def save(Family family) {
        if (family == null) {
            notFound()
            return
        }

        try {
            familyService.save(family)
        } catch (ValidationException e) {
            respond family.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'family.label', default: 'Family'), family.id])
                redirect family
            }
            '*' { respond family, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond familyService.get(id)
    }

    def update(Family family) {
        if (family == null) {
            notFound()
            return
        }

        try {
            familyService.save(family)
        } catch (ValidationException e) {
            respond family.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'family.label', default: 'Family'), family.id])
                redirect family
            }
            '*'{ respond family, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        familyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'family.label', default: 'Family'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'family.label', default: 'Family'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
