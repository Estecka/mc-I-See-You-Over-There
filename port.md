# Minecraft Code Breaking Changes
### 1.19.4
Initial Release

### 1.20.5
#### No Workaround:
- `GameRenderer::renderWorld` has one less parameter. No code change required, but needs recompilation.

### 1.21.0
### No Workaround:
- `GameRenderer::renderWorld` Now takes a RenderTickCounter. No code change required, but needs recompilation.
### Possible Workaround:
- `ThreadedAnvilChunkStorage` was renamed to `ServerChunkLoadingManager` (Yarn Mapping changes)
