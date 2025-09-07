package ahmed.test.monolithic.monolithic_mod.subjects.shared;

import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.NamedInterface;
import org.springframework.modulith.PackageInfo;

@PackageInfo
@NamedInterface("subject.shared")
@ApplicationModule(type = ApplicationModule.Type.OPEN)
public class ModuleMetadata {}

