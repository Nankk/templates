from setuptools import setup
import {{sanitized}}

DESCRIPTION = ""
NAME = "{{sanitized}}"
AUTHOR = ""
AUTHOR_EMAIL = ""
URL = ""
LICENSE = ""
DOWNLOAD_URL = ""
VERSION = {{sanitized}}.__version__
PYTHON_REQUIRES = ">=3.8"

INSTALL_REQUIRES = [
    # "numpy >=1.20.3",
]

EXTRAS_REQUIRE = {
    # "test": ["pytest"]
}

PACKAGES = [
    "{{sanitized}}"
]

CLASSIFIERS = []

with open("README.md", "r") as fp: readme = fp.read()
long_description = readme

setup(name=NAME,
      author=AUTHOR,
      author_email=AUTHOR_EMAIL,
      maintainer=AUTHOR,
      maintainer_email=AUTHOR_EMAIL,
      description=DESCRIPTION,
      long_description=long_description,
      license=LICENSE,
      url=URL,
      version=VERSION,
      download_url=DOWNLOAD_URL,
      python_requires=PYTHON_REQUIRES,
      install_requires=INSTALL_REQUIRES,
      extras_require=EXTRAS_REQUIRE,
      packages=PACKAGES,
      classifiers=CLASSIFIERS
    )
