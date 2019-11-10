package examples.hackathon;

import com.intuit.karate.junit5.Karate;

class HackathonRunner {

    @Karate.Test
    Karate testUsers() {
        return new Karate().feature("hackathon").relativeTo(getClass());
    }

}
