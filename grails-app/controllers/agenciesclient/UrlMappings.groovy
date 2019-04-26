package agenciesclient

class UrlMappings {

    static mappings = {

//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }

        "/"(controller: 'client')
        "/obtenerDatos"(controller: 'client', action: 'consultarapi')

        "/add/$id"(controller: 'client', action: 'add')
        "/delete/$id"(controller: 'client', action: 'delete')

        "/agency"(resources:'agency') {
            "/address"(resources:'address')
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
