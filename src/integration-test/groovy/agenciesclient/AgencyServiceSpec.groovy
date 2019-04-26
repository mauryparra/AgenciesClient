package agenciesclient

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AgencyServiceSpec extends Specification {

    AgencyService agencyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Agency(...).save(flush: true, failOnError: true)
        //new Agency(...).save(flush: true, failOnError: true)
        //Agency agency = new Agency(...).save(flush: true, failOnError: true)
        //new Agency(...).save(flush: true, failOnError: true)
        //new Agency(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //agency.id
    }

    void "test get"() {
        setupData()

        expect:
        agencyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Agency> agencyList = agencyService.list(max: 2, offset: 2)

        then:
        agencyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        agencyService.count() == 5
    }

    void "test delete"() {
        Long agencyId = setupData()

        expect:
        agencyService.count() == 5

        when:
        agencyService.delete(agencyId)
        sessionFactory.currentSession.flush()

        then:
        agencyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Agency agency = new Agency()
        agencyService.save(agency)

        then:
        agency.id != null
    }
}
