// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.hadoop.distribution.hdp220;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.talend.core.hadoop.version.EHadoopDistributions;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;
import org.talend.hadoop.distribution.AbstractDistribution;
import org.talend.hadoop.distribution.ComponentType;
import org.talend.hadoop.distribution.DistributionModuleGroup;
import org.talend.hadoop.distribution.EHadoopVersion;
import org.talend.hadoop.distribution.NodeComponentTypeBean;
import org.talend.hadoop.distribution.component.HBaseComponent;
import org.talend.hadoop.distribution.component.HCatalogComponent;
import org.talend.hadoop.distribution.component.HDFSComponent;
import org.talend.hadoop.distribution.component.HiveComponent;
import org.talend.hadoop.distribution.component.MRComponent;
import org.talend.hadoop.distribution.component.PigComponent;
import org.talend.hadoop.distribution.component.SqoopComponent;
import org.talend.hadoop.distribution.constants.MRConstant;
import org.talend.hadoop.distribution.constants.PigOutputConstant;
import org.talend.hadoop.distribution.hdp220.modulegroup.HDP220MRS3NodeModuleGroup;
import org.talend.hadoop.distribution.hdp220.modulegroup.HDP220PigModuleGroup;
import org.talend.hadoop.distribution.hdp220.modulegroup.HDP220PigOutputNodeModuleGroup;

public class HDP220Distribution extends AbstractDistribution implements HDFSComponent, MRComponent, HBaseComponent,
        SqoopComponent, PigComponent, HiveComponent, HCatalogComponent {

    public final static String VERSION = EHadoopVersion4Drivers.HDP_2_2.getVersionValue();

    private final static String YARN_APPLICATION_CLASSPATH = "$HADOOP_CONF_DIR,/usr/hdp/current/hadoop-client/*,/usr/hdp/current/hadoop-client/lib/*,/usr/hdp/current/hadoop-hdfs-client/*,/usr/hdp/current/hadoop-hdfs-client/lib/*,/usr/hdp/current/hadoop-mapreduce-client/*,/usr/hdp/current/hadoop-mapreduce-client/lib/*,/usr/hdp/current/hadoop-yarn-client/*,/usr/hdp/current/hadoop-yarn-client/lib/*"; //$NON-NLS-1$

    private static Map<ComponentType, Set<DistributionModuleGroup>> moduleGroups;

    private static Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> nodeModuleGroups;

    static {
        moduleGroups = new HashMap<>();
        moduleGroups.put(ComponentType.PIG, HDP220PigModuleGroup.getModuleGroups());

        nodeModuleGroups = new HashMap<>();
        nodeModuleGroups.put(new NodeComponentTypeBean(ComponentType.PIG, PigOutputConstant.PIGSTORE_COMPONENT),
                HDP220PigOutputNodeModuleGroup.getModuleGroups());
        nodeModuleGroups.put(new NodeComponentTypeBean(ComponentType.MAPREDUCE, MRConstant.S3_INPUT_COMPONENT),
                HDP220MRS3NodeModuleGroup.getModuleGroups());
        nodeModuleGroups.put(new NodeComponentTypeBean(ComponentType.MAPREDUCE, MRConstant.S3_OUTPUT_COMPONENT),
                HDP220MRS3NodeModuleGroup.getModuleGroups());
    }

    @Override
    public String getDistribution() {
        return EHadoopDistributions.HORTONWORKS.getName();
    }

    @Override
    public String getDistributionName() {
        return EHadoopDistributions.HORTONWORKS.getDisplayName();
    }

    @Override
    public String getVersion() {
        return EHadoopVersion4Drivers.HDP_2_2.getVersionValue();
    }

    @Override
    public String getVersionName(ComponentType componentType) {
        return EHadoopVersion4Drivers.HDP_2_2.getVersionDisplay();
    }

    @Override
    public EHadoopVersion getHadoopVersion() {
        return EHadoopVersion.HADOOP_2;
    }

    @Override
    public boolean doSupportKerberos() {
        return true;
    }

    @Override
    public Set<DistributionModuleGroup> getModuleGroups(ComponentType componentType) {
        return moduleGroups.get(componentType);
    }

    @Override
    public Set<DistributionModuleGroup> getModuleGroups(ComponentType componentType, String componentName) {
        return nodeModuleGroups.get(new NodeComponentTypeBean(componentType, componentName));
    }

    @Override
    public boolean doSupportCrossPlatformSubmission() {
        return true;
    }

    @Override
    public String getYarnApplicationClasspath() {
        return YARN_APPLICATION_CLASSPATH;
    }

    @Override
    public boolean doSupportSequenceFileShortType() {
        return true;
    }

    @Override
    public boolean doSupportUseDatanodeHostname() {
        return true;
    }

    @Override
    public boolean doSupportNewHBaseAPI() {
        return true;
    }

    @Override
    public boolean doJavaAPISupportStorePasswordInFile() {
        return true;
    }

    @Override
    public boolean doJavaAPISqoopImportSupportDeleteTargetDir() {
        return true;
    }

    @Override
    public boolean doJavaAPISqoopImportAllTablesSupportExcludeTable() {
        return true;
    }

    @Override
    public boolean doSupportHCatalog() {
        return true;
    }

    @Override
    public boolean pigVersionPriorTo_0_12() {
        return false;
    }

    @Override
    public boolean doSupportHBase() {
        return true;
    }

    @Override
    public boolean doSupportTezForPig() {
        return true;
    }

    @Override
    public boolean doSupportImpersonation() {
        return true;
    }

    @Override
    public boolean doSupportEmbeddedMode() {
        return true;
    }

    @Override
    public boolean doSupportStandaloneMode() {
        return true;
    }

    @Override
    public boolean doSupportHive1() {
        return true;
    }

    @Override
    public boolean doSupportHive2() {
        return true;
    }

    @Override
    public boolean doSupportTezForHive() {
        return true;
    }

    @Override
    public boolean doSupportHBaseForHive() {
        return true;
    }

    @Override
    public boolean doSupportSSL() {
        return true;
    }

    @Override
    public boolean doSupportHive1Standalone() {
        return false;
    }

    @Override
    public boolean doSupportORCFormat() {
        return true;
    }

    @Override
    public boolean doSupportAvroFormat() {
        return true;
    }

    @Override
    public boolean doSupportParquetFormat() {
        return true;
    }

    @Override
    public boolean doSupportStoreAsParquet() {
        return false;
    }

}
