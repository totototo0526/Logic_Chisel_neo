# Config.java Cleanup Task

- [ ] Unused imports removal
    - [ ] `java.util.Set`
    - [ ] `java.util.stream.Collectors`
    - [ ] `net.minecraft.world.item.Item`
    - [ ] `net.neoforged.bus.api.SubscribeEvent`
    - [ ] `net.neoforged.fml.common.EventBusSubscriber`
    - [ ] `net.neoforged.fml.event.config.ModConfigEvent`
- [ ] Null safety warnings fix
    - [ ] Address warning at `ITEM_STRINGS` definition (Line 35)
    - [ ] Address warning at `validateItemName` (Line 40)
- [ ] Verification
    - [ ] Verify file compiles (manual check of imports)
