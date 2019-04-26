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

        "/agency"(resources:'agency') {
            "/address"(resources:'address')
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
