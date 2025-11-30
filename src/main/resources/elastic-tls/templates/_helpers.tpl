{{- define "elastic.name" -}}
elasticsearch
{{- end }}

{{- define "elastic.fullname" -}}
{{ include "elastic.name" . }}
{{- end }}
