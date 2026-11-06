# `-----` Testing Benchmark

## Objective

Create a test plan and write JUnit tests for the provided feeding-round
component. You may use the user stories and acceptance criteria, Javadoc
contracts, and implementation when selecting cases.

## Integrity Requirements

In order to not receive an automatic 0 for this, you must be recording your entire monitor (not just a window capture)
to the Zoom cloud, which requires logging into Zoom using SSO so that you are logged in through Clemson, as cloud
recording is not a free feature.

Your recording must begin before any changes are made (ideally before you even clone).

Once you complete the benchmark, make your submission to gradescope, end the zoom recording, grab the cloud recording link,
and submit that link to canvas. Failure to submit a watchable zoom recording link that clearly shows your entire monitor
(again, not just a single window) could, and likely will, result in an automatic zero.

If you're going to use the internet, which is permitted outside of anything AI related but still not recommended,
you can add `-AI` to your google query and it will prevent the Google AI overview from appearing.

## Forbidden Plugins

Any plug-in related to AI is strictly forbidden on this assignment. This includes, but is not limited to:

- Full Line Code Completion (often enabled by default)
- GitHub Copilot
- JetBrains AI Assistant
- Junie
- Whatever Claude uses

I will not accept "playing ignorant" and not knowing whether something is an AI plugin as to why you should
get a 0 if you're caught using something you're not supposed to. Be an adult. If you know something is
writing code or suggesting code for you, then 99.99% it's AI related.

IntelliJ making suggestions when you have an error (by hovering over the error line) is not AI. That's fine

## Files

- `-----` — the `-----` interface and its contracts
- `-----` — the `-----` implementation
- [user-stories.md](docs/user-stories.md) — user stories and acceptance criteria
- `-----` — the test-plan template
- `-----` - The JUnit test suite

## Part 1: Test plan

Complete `------` with exactly one test case for each of
these five methods specifically:

1. `-----`
2. `-----`
3. `-----`
4. `-----`
5. `-----`

Choose a specific case for each target. Describe the method inputs, expected
outputs, input state, and expected output state. Use `N/A`
where a column does not apply. How you specifically fill out each column is somewhat
up to you, so long as you're putting logical, relevant, and correct information in each one.

In the **Case Origin** column, identify plainly where the case came from.
For example, you may identify a specific acceptance criterion, a Javadoc
contract condition, or an implementation branch. Be specific enough that
someone can find the relevant condition.

**A single case does not need to cover every behavior of its target.**

## Part 2: JUnit tests

Create the JUnit tests you specified in your test plan within `-----`. As you write the JUnit tests, keep in mind:

- You're not _entirely_ being graded on "good" JUnit tests, more than you're being graded on just having
  **meaningful** JUnit tests that are still capable of finding _A_ failure. You don't need to find all possible failures.
- If a test fails, a penalty will be applied
- Doing something that forces the test to pass when it shouldn't will result in a loss of all credit for that test. This includes
    - Simply just writing blank tests.
    - returning mid-test case before something would stop it from passing.
    - Not asserting anything
    - Not testing anything even remotely related to what you're supposed to be testing.

The observation methods `-----` and `-----` may be used in your tests.
You do not need to create separate test cases for them.

## Submission

Once you finish, you need to do a few things, **in this order**:

1. Commit + push all changes to GitHub **FROM IntelliJ SPECIFICALLY! COMMITTING IN GITHUB'S TEXT EDITOR WILL RESULT IN A SEVERE PENALTY**
2. Submit the entire repo to Gradescope. Do not upload individual files.
    1. Submit here: `-----`
3. Stop your zoom cloud recording
4. submit the zoom cloud recording to canvas
    1. Find the recording here (click the share arrow, even if it's still uploading/processing, and copy link): https://clemson.zoom.us/recording/
    2. Submit here: `-----`
5. **Sign out at the front with Matthew**
6. Leave.