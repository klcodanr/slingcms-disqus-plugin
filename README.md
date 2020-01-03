<!--
 * Copyright (C) 2019 Dan Klco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 -->
# Apache Sling CMS - Disqus Plugin

A plugin to enable [disqus](https://disqus.com) comments in  the
[Apache Sling CMS](https://github.com/apache/org-apache-sling-app-cms).

## Installation

Download the [build the code](#building) or latest release and install it into an
Apache Sling CMS instance via the OSGi console at [sling_cms_host]/system/console/bundles.

## Configuration

To use this plugin, you must enable it in the [Component Policy](https://github.com/apache/sling-org-apache-sling-app-cms/blob/master/docs/component-policy.md)
you want in Sling CMS and configure the Component Policy with your Disqus Comments configuration.

The configuration includes the following fields:

  - **Disqus Short Name** (required) - Your [Disqus shortName](https://help.disqus.com/en/articles/1717111-what-s-a-shortname)
  - **Page URL Prefix** (optional) - A prefix for generating the URL for the discussion, by default the mapped relative URL will be used
  - **Page URL Suffix** (optional) - A suffix for generating the URL for the discussion, .html will be used by default

## Use

Once the Disqus Comments component is configured, you can add it to any page by selecting + then Disqus > Disqus Comments.

You can also edit the Disqus Comments component to override the generated URL, however this is not required.

The component can also be included from any other component using the following code:

  `<sling:include path="[some-path]" resourceType="com-danklco/disqus/compoents/disqus-comments" />`

## Building

This project requires Apache Maven 3 and Java JDK8. To build the project run:

`mvn clean install`

To install the bundle into a local Sling CMS instance running on port 8080, run:

`mvn clean install -P autoInstallBundle`
