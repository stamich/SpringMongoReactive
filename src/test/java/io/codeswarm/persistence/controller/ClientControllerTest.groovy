package io.codeswarm.persistence.controller

import io.codeswarm.persistence.model.Client
import io.codeswarm.persistence.repository.ClientRepository
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Subject

class ClientControllerTest extends Specification {

    def repository = Mock(ClientRepository)

    def @Subject api = new ClientController(clientRepository: repository)

    def mockMvc = MockMvcBuilders.standaloneSetup(api).build()

    def 'should call rest controller' () {

        when:
        def result = mockMvc.perform(get('/client'))

        then:
        1 * repository.findAll() >> [new Client('Adam', 'Kowalski', 'adam@com.com')]
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)
                .andExpect(content().string('[{"firstName" : "Adam", "lastName" : "Kowalski", "email" : "adam@com.com" }]')))
    }
}
