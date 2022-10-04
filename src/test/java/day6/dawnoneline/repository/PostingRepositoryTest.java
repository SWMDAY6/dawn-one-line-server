package day6.dawnoneline.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import day6.dawnoneline.domain.Posting;

@SpringBootTest
class PostingRepositoryTest {

    @Autowired
    PostingRepository postingRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testPosting() {
        Posting posting = new Posting();
        posting.setContent("안녕하세요");
        posting.setCoordinate(127.1, 38.2);
        posting.setPassword("12344");
        Long saveId = postingRepository.save(posting);

        Posting findPosting = postingRepository.find(saveId);
        System.out.println("변경 후 : " + findPosting.getId() + findPosting.getContent());

    }
}