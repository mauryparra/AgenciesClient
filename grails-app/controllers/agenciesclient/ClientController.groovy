package agenciesclient

import grails.converters.JSON
import groovy.json.JsonSlurper

class ClientController {

    def index() {

    }

    def consultarapi() {

        def parametros = "?site_id=" + params.site_id + "&payment_method_id=" + params.payment_method_id +
                "&near_to=" + params.lat + "," + params.long + "," + params.radio +
                "&limit=" + params.limit + "&offset=" + params.offset + "&order_by=" + params.order_by

        def url = new URL("http://localhost:4567/agencias" + parametros)
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzila/5.0")

        JsonSlurper json = new JsonSlurper()
        def result = json.parse(connection.getInputStream())

        [agencies: result]
    }
}
