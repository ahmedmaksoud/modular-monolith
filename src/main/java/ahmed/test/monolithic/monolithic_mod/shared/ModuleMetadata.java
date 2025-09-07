package ahmed.test.monolithic.monolithic_mod.shared;

import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.NamedInterface;
import org.springframework.modulith.PackageInfo;

@PackageInfo
@NamedInterface("shared")
@ApplicationModule(type = ApplicationModule.Type.OPEN)
public class ModuleMetadata {
}