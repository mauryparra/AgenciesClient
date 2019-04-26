package agenciesclient

import grails.gorm.services.Service

@Service(Agency)
interface AgencyService {

    Agency get(Serializable id)

    List<Agency> list(Map args)

    Long count()

    void delete(Serializable id)

    Agency save(Agency agency)

}