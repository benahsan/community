package community

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TravelServiceSpec extends Specification {

    TravelService travelService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Travel(...).save(flush: true, failOnError: true)
        //new Travel(...).save(flush: true, failOnError: true)
        //Travel travel = new Travel(...).save(flush: true, failOnError: true)
        //new Travel(...).save(flush: true, failOnError: true)
        //new Travel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //travel.id
    }

    void "test get"() {
        setupData()

        expect:
        travelService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Travel> travelList = travelService.list(max: 2, offset: 2)

        then:
        travelList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        travelService.count() == 5
    }

    void "test delete"() {
        Long travelId = setupData()

        expect:
        travelService.count() == 5

        when:
        travelService.delete(travelId)
        sessionFactory.currentSession.flush()

        then:
        travelService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Travel travel = new Travel()
        travelService.save(travel)

        then:
        travel.id != null
    }
}
