package testviews

class UserDomain {

    String name

    static constraints = {
    }

    static List<MyMessage> findUnseenMessageForUser(UserDomain user) {
        MyMessage.executeQuery(
                'select m from MyMessage m where :user not in elements(m.views)',[user: user]
        ).sort{it.id}
    }
}
