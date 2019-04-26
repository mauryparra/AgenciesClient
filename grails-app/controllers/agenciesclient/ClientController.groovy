package agenciesclient

import groovy.json.JsonSlurper

class ClientController {

    static HashMap<String, Agency> agenciasMap = new HashMap<String, Agency>();

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

        for (item in result) {
            Address direccion = new Address()
            direccion.address_line = item.address.address_line
            direccion.city = item.address.city
            direccion.country = item.address.country
            direccion.location = item.address.location
            direccion.other_info = item.address.other_info
            direccion.state = item.address.state
            direccion.zip_code = item.address.zip_code

            Agency agencia = new Agency()
            agencia.address = direccion
            agencia.agency_code = item.agency_code
            agencia.correspondent_id = item.correspondent_id
            agencia.description = item.description
            agencia.disabled = item.disabled
            agencia.distance = item.distance
            agencia.id = item.id
            agencia.payment_method_id = item.payment_method_id
            agencia.phone = item.phone
            agencia.site_id = item.site_id
            agencia.terminal = item.terminal

            agenciasMap.put(agencia.agency_code, agencia)
        }

        [agencies: result]
    }

    def add() {
        Agency agencia = agenciasMap.get(params.id)
        if (agencia != null) {
            agencia.save()
        }
    }

    def remove() {
        Agency agencia = agenciasMap.get(params.id)
        if (agencia != null) {
            agencia.delete()
        }
    }
}
