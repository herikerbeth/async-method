package demo;

import demo.service.LookupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private LookupService lookupService;

	@Test
	void contextLoads() {
		assertThat(lookupService).isNotNull();
	}
}
