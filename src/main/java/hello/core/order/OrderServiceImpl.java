package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Lombok (Annotation Processing) : "final"이 붙은 필드를 모아 생성자를 자동으로 생성
public class OrderServiceImpl implements OrderService {

    /*******************************************************************************************
     * 필드 주입
     ******************************************************************************************/
    /* @Autowired */ private final MemberRepository memberRepository;
    /* @Autowired */ private final DiscountPolicy discountPolicy;

    /*******************************************************************************************
     * 수정자 주입
     *******************************************************************************************
     @Autowired
     public void setMemberRepository(MemberRepository memberRepository) {
         this.memberRepository = memberRepository;
     }

     @Autowired
     public void setDiscountPolicy(DiscountPolicy discountPolicy) {
         this.discountPolicy = discountPolicy;
     }
     *******************************************************************************************/

    /*******************************************************************************************
     * 일반 메서드 주입
     *******************************************************************************************
     @Autowired
     public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
         this.memberRepository = memberRepository;
         this.discountPolicy = discountPolicy;
     }
     *******************************************************************************************/

   /*******************************************************************************************
    * 생성자 주입 (생성자가 한 개일 경우 @Autowired 생략 가능)
    *******************************************************************************************
    @Autowired // ac.getBean(MemberRepository.class), ac.getBean(DiscountPolicy.class)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    *******************************************************************************************/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
