package george.wordcount.ui;

import george.wordcount.logic.MockWordCounter;
import george.wordcount.logic.WordCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

class UserInteractionFlowIntegrationTest {

    private static class TestableUserInteractionFlow extends UserInteractionFlow {
        private final String testInput;
        private final List<String> testOutput;

        public TestableUserInteractionFlow(WordCounter wordCounter, String testInput, List<String> testOutput) {
            super(wordCounter);
            this.testInput = testInput;
            this.testOutput = testOutput;
        }

        @Override
        protected void printText(String text) {
            // collect all the output we print
            this.testOutput.add(text);
        }

        @Override
        protected String promptUserForString(String inputText) {
            return this.testInput;
        }
    }

    static Stream<Arguments> dataProviderFor_GIVEN_userInteractionFlow_started_WHEN_user_input_is_entered_THEN_output_is_as_expected() {
        return Stream.of(
                Arguments.of(" This is test  ", 3, "Number of words: 3"),
                Arguments.of("Mary had a little lamb", 4, "Number of words: 4")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviderFor_GIVEN_userInteractionFlow_started_WHEN_user_input_is_entered_THEN_output_is_as_expected")
    public void GIVEN_userInteractionFlow_started_WHEN_user_input_is_entered_THEN_output_is_as_expected(
            final String input,
            final int wordCount,
            final String expectedOutput) {
        // GIVEN:
        final LinkedList<String> output = new LinkedList<>();
        final WordCounter wordCounter = new MockWordCounter(input, wordCount);
        final UserInteractionFlow classUnderTest =
                new TestableUserInteractionFlow(wordCounter, input, output);

        // WHEN:
        classUnderTest.doInteraction(null);

        // THEN:
        Assertions.assertEquals(1, output.size(), "we only expect one output call");
        Assertions.assertEquals(expectedOutput, output.get(0));
    }
}
