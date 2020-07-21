#include "core.h"
#include <string>

namespace {{sanitized}} {
  std::string Hello::Greeting(const std::string name) {
    return "Hello, " + name;
  }
}
