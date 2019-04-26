package agenciesclient

import grails.gorm.services.Service

@Service(Address)
interface AddressService {

    Address get(Serializable id)

    List<Address> list(Map args)

    Long count()

    void delete(Serializable id)

    Address save(Address address)

}