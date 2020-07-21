#include "core.h"
#include "gtest/gtest.h"
using sut = {{sanitized}}::Hello;

TEST(GreetingTest, Kurt) {
  EXPECT_EQ("Hello, hello, hello, hello, how-low?",
            sut::Greeting("hello, hello, hello, how-low?"));
}

