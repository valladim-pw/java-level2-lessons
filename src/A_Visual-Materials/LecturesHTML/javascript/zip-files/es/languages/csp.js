/*! `csp` grammar compiled for Highlight.js 11.11.1 */
var hljsGrammar = (function () {
  'use strict';

  /*
  Language: CSP
  Description: Content Security Policy definition highlighting
  Author: Taras <oxdef@oxdef.info>
  Website: https://developer.mozilla.org/en-US/docs/Web/HTTP/CSP
  Category: web

  vim: ts=2 sw=2 st=2
  */

  /** @type LanguageFn */
  function csp(hljs) {
    const KEYWORDS = [
      "base-uri",
      "child-src",
      "connect-src",
      "default-src",
      "font-src",
      "form-action",
      "frame-ancestors",
      "frame-src",
      "img-src",
      "manifest-src",
      "media-src",
      "object-src",
      "plugin-types",
      "report-uri",
      "sandbox",
      "script-src",
      "style-src",
      "trusted-types",
      "unsafe-hashes",
      "worker-src"
    ];
    return {
      name: 'CSP',
      case_insensitive: false,
      keywords: {
        $pattern: '[a-zA-Z][a-zA-Z0-9_-]*',
        keyword: KEYWORDS
      },
      contains: [
        {
          className: 'string',
          begin: "'",
          end: "'"
        },
        {
          className: 'attribute',
          begin: '^Content',
          end: ':',
          excludeEnd: true
        }
      ]
    };
  }

  return csp;

})();
;
export default hljsGrammar;