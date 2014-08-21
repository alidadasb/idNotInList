package testviews

import spock.lang.Specification

/**
 *
 */
class MyMessageSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        setup:
        def m1 = new MyMessage(content: "message 1")
        def m2 = new MyMessage(content: "message 2")
        def m3 = new MyMessage(content: "message 3").save(flush: true)

        def u1 = new UserDomain(name: "user1").save(flush: true)
        def u2 = new UserDomain(name: "user2").save(flush: true)
        def u3 = new UserDomain(name: "user3").save(flush: true)
        def u4 = new UserDomain(name: "user4").save(flush: true)

        when:
        m1.addToViews u1

        m2.addToViews u1
        m2.addToViews u3
        m2.addToViews u4

        m1.save(flush: true)
        m2.save(flush: true)

        then:
        println "UserDomain.findUnseenMessageForUser(u2): " + UserDomain.findUnseenMessageForUser(u2)
        UserDomain.findUnseenMessageForUser(u2).containsAll([m1, m2, m3])
    }

    void "test something 2 "() {
        setup:
        def m1 = new MyMessage(content: "message 1")
        def m2 = new MyMessage(content: "message 2")
        def m3 = new MyMessage(content: "message 3").save(flush: true)

        def u1 = new UserDomain(name: "user1").save(flush: true)
        def u2 = new UserDomain(name: "user2").save(flush: true)
        def u3 = new UserDomain(name: "user3").save(flush: true)
        def u4 = new UserDomain(name: "user4").save(flush: true)

        when:
        m1.addToViews u1

        m2.addToViews u1
        m2.addToViews u3
        m2.addToViews u4

        m1.save(flush: true)
        m2.save(flush: true)

        then:
        println "UserDomain.findUnseenMessageForUser(u1): " + UserDomain.findUnseenMessageForUser(u1)
        UserDomain.findUnseenMessageForUser(u1).containsAll([m3])

    }
}
