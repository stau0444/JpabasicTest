import jpaTest.domain.AddressHistory;
import jpaTest.domain.Member;
import jpaTest.domain.Team;
import jpaTest.domain.embededdTest.Address;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address1 = new Address("street1","12331");
            Address address2 = new Address("street2","12332");
            Address address3 = new Address("street3","12333");

            Member member = new Member();
            member.setName("member1");
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("고기");

            member.getAddressHistories().add(new AddressHistory(address1));
            member.getAddressHistories().add(new AddressHistory(address2));
            member.getAddressHistories().add(new AddressHistory(address3));

            em.persist(member);

            em.flush();
            em.clear();

            Member member1 = em.find(Member.class, member.getId());
            //값 타입 리스트 테이블을 모두 지우고   다시 넣는다 .
            member1.getFavoriteFoods().remove("피자");

            System.out.println(member1.getFavoriteFoods().get(1));
            System.out.println(member1.getFavoriteFoods());

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }

    //멤버만 필요한 경우
    private static void printMember(Member member) {
        String name = member.getName();
        System.out.println("memberName = " + name);
    }

    //멤버와 팀이 모두 필요한 경우
    private static void printMemberAndTeam(Member member) {
        String name = member.getName();
        System.out.println("member = " + name);

        Team team = member.getTeam();
        String teamName = team.getName();
        System.out.println("teamName = " + teamName);
    }
}
