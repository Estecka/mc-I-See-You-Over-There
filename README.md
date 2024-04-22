# I See You Over There !

Adds more options to change the render distance of entities and block-entities.  
Can be used to fix the lower render distance of small entities, or automatically increase the render distance when zooming in.
This should theoretically work with any zoom mod.

**This will not work as effectively in multiplayer.** Servers have their own limit on how far away the client can know about entities.

## Configuration

The config can be edited in-game using Mod Menu + Cloth Config

All distances are measured in block.

### `fovScaling` (boolean)
When enabled, entity and block-entity render distances will be adjusted based on the FOV. (e.g, when using a spyglass).
The identity is a FOV of 90°.

When enabled, all other settings scales accordingly, except for `entity.max`.

### `entity.min` (integer)
Forces all entities within this range to be rendered, regardless of their size.  
This setting *is* affected by FOV-scaling.

### `entity.max` (integer)
Prevents entities beyond this range from being rendered, regardless of their size.

This setting is intended to serve as a limiter. **It is not affected by FOV-scaling**, and will override the Minimum value if they end up conflicting.

### `block.min` (integer)
Renders all block-entities within this range. This can only increase block-entity render distance, not reduce it.

### `block.beacon` (integer)
Render distance for the beacon's beam.
