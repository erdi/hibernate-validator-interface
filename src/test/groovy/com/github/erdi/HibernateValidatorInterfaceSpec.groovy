package com.github.erdi

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.constraints.NotNull

class HibernateValidatorInterfaceSpec extends Specification {

    def "validation annotations on getters in interfaces are applied to implementing classes"() {
        given:
        def validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .validator

        when:
        def violations = validator.validate(new ABean())

        then:
        violations*.message == ["must not be null"]
    }


    interface AnInterface {
        @NotNull
        String getSomeProperty()
    }

    class ABean implements AnInterface {
        String someProperty
    }
}
