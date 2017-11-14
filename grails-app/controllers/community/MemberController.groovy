package community

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@SuppressWarnings('LineLength')
class MemberController {

    def exportService

    MemberService memberService
    UploadMemberFeaturedImageService uploadMemberFeaturedImageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond memberService.list(params), model:[memberCount: memberService.count()]
    }

    def resident(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Member.findAllByTravellingNotEqual(true, params), model:[memberCount: memberService.count()]
    }

    def travelling(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Member.findAllByTravelling(true, params), model:[memberCount: memberService.count()]
    }

    def arab(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Member.findAll("from Member as m where m.country.region=?", ["العرب"] , params), model:[memberCount: memberService.count()]
    }

    def west(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Member.findAll("from Member as m where m.country.region=?", ["الغرب"] , params), model:[memberCount: memberService.count()]
    }

    def asia(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Member.findAll("from Member as m where m.country.region=?", ["شرق آسيا"] , params), model:[memberCount: memberService.count()]
    }

    @Transactional(readOnly = true)
    def featuredImage(Member member) {
        if (member == null || member.featuredImageBytes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        render file: member.featuredImageBytes, contentType: member.featuredImageContentType
    }

    @Transactional(readOnly = true)
    def editFeaturedImage(Member member) {
        respond member
    }

    def uploadFeaturedImage(FeaturedImageCommand cmd) {
        if (cmd == null) {
            notFound()
            return
        }

        if (cmd.hasErrors()) {
            respond(cmd.errors, model: [member: cmd], view: 'editFeaturedImage')
            return
        }

        def member = uploadMemberFeaturedImageService.uploadFeatureImage(cmd)

        if (member == null) {
            notFound()
            return
        }

        if (member.hasErrors()) {
            respond(member.errors, model: [member: member], view: 'editFeaturedImage')
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'member.label', default: 'Member'), member.id])
                redirect member
            }
            '*' { respond member, [status: OK] }
        }
    }

    def exportExcel() {
        params.exportFormat = "excel"
        params.extension = "xls"

        if(params.exportFormat && params.exportFormat != "html") {

            def members = Member.list(params)

            def results = []
            members.each { m ->
                def map = [:]
               
                map.put("name",m.name)
                map.put("relationship",m.relationship)
                map.put("headOfFamily",m.headOfFamily)
                map.put("nameInPassport",m.nameInPassport)
                map.put("generalNumber",m.generalNumber)
                map.put("gender",m.gender)
                map.put("birth",m.birth)
                map.put("age",m.age)
                map.put("job",m.job)
                map.put("country",m.country)
                map.put("studies",m.studies)
                map.put("travelling",m.travelling)
                map.put("travel",m.travel)
                map.put("deposit",m.deposit)
                results.add(map)
            }            

			response.contentType = grailsApplication.config.grails.mime.types[params.exportFormat]
			response.setHeader("Content-disposition", "attachment; filename=members.xls")

            List fields = [ "name", "relationship", "headOfFamily", "nameInPassport", "generalNumber", "gender", "birth", "age", "job", "country", "studies", "travelling", "travel", "deposit"]

            Map labels = ["name": "الاسم", "relationship": "صلة برب الأسرة", "headOfFamily": "رب الأسرة" , "nameInPassport": "الاسم في الجواز", "generalNumber":"رقم العام" , 
            "gender":"الجنس" , "birth":"تاريخ الولادة" , "age":"العمر" , "job":"المهنة", "country":"البلاد" , "studies":"الدراسة" , "travelling":"هل مسافر؟" , "travel":"السفر" , "deposit":"الضمان"]

            Map formatters = [:]
            Map parameters = [:]

            exportService.export(params.exportFormat, response.outputStream, results, fields, labels, formatters, parameters)
        }
    }

    def show(Long id) {
        respond memberService.get(id)
    }

    def create() {
        respond new Member(params)
    }

    def save(Member member) {
        if (member == null) {
            notFound()
            return
        }

        try {
            if (member.birth) member.age=new Date().getYear() - member.birth.getYear()
            memberService.save(member)
        } catch (ValidationException e) {
            respond member.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'member.label', default: 'Member'), member.id])
                redirect member
            }
            '*' { respond member, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond memberService.get(id)
    }

    def update(Member member) {
        if (member == null) {
            notFound()
            return
        }

        try {
            if (member.birth) member.age=new Date().getYear() - member.birth.getYear()
            memberService.save(member)
        } catch (ValidationException e) {
            respond member.errors, view:'edit'
            return
        }
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'member.label', default: 'Member'), member.id])
                redirect member
            }
            '*'{ respond member, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        memberService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'member.label', default: 'Member'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
