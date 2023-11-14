# Simplifier Captcha-Plugin

## Introduction

The Captcha-Plugin is an extension to [Simplifier](http://simplifier.io), adding the capability of using 
[web captcha technique](https://en.wikipedia.org/wiki/CAPTCHA) for securing an applications login page against automated attacks. 

Simplifier Community docs: [https://community.simplifier.io](https://community.simplifier.io/doc/current-release/extend/plugins/list-of-plugins/)


## Usage
Can be used e.g. like this in an Client BO with output results session and image:
```js
Simplifier.Plugin.captcha.demandCaptcha({}, cb_demandCaptcha.bind(this), false, true, cb_error, true);

function cb_demandCaptcha(po_result) {
  fnSuccess({image: "data:image/jpg;base64," + po_result.result.jpegImage, session: po_result.result.session});
}

function cb_error(ps_error) {
  throw new Error("Could not reload Captcha. Please try again.");
}
```

## Deployment

### Docker Image

The build creates a base image with the necessary files to add to the Simplifier appserver container.

#### Prerequisites

docker

Scala 2.12, sbt

JDK of the target platform (GraalVM-CE 20.0.2)

#### build 
```bash
build/artifacts$ ./build.sh
```

#### run the test
At the commandline, run
```bash
sbt test
```

#### run locally during development
At the commandline, run
```bash
sbt run
```

#### Configuration

The created image is named after the sbt projects name and version. So the result would be something like "captchaplugin:0.0.1-SNAPSHOT"

Settings and commandline arguments may be changed by editing [./deployment/assets](./deployment/assets)

The setup process itself is defined by [./deployment/setup.sh](./deployment/setup.sh). 


### Appserver build

The docker image from the previous step has to be available in the registry when an appserver image is built.

In the appserver Dockerfile, refer to it as follows:

```dockerfile
FROM captchaplugin:0.0.1-SNAPSHOT as captchaplugin
```

Below  ```FROM simplifierag/simplifierbase```, insert the following lines:
```dockerfile
# install CAPTCHA plugin
COPY --from=captchaplugin /opt/plugin /tmp/captchaplugin
RUN /tmp/captchaplugin/setup.sh /opt/simplifier
RUN rm -Rf /tmp/captchaplugin
```

### Manual build

Config property ```plugin.fileSystemRepository.cacheFolder``` should point to a writable path.
