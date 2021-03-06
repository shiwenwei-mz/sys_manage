/**
 * Copyright © 2018 TaoYu (tracy5546@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sys.manage.config.druid;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TaoYu Kanyuxia
 * @since 1.0.0
 */
public class YmlDynamicDataSourceProvider extends AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider {

  private DynamicDataSourceProperties properties;

  public YmlDynamicDataSourceProvider(DynamicDataSourceProperties properties) {
    this.properties = properties;
  }

  @Override
  public DataSource loadMasterDataSource() {
    return createDataSource(properties.getMaster());
  }

  @Override
  public Map<String, DataSource> loadMultiDataSource() {
    Map<String, DynamicItemDataSourceProperties> slaves = properties.getMulti();
    Map<String, DataSource> dataSourceMap = new HashMap<>(slaves.size());
    slaves.forEach((k, v) -> dataSourceMap.put(k, createDataSource(v)));
    return dataSourceMap;
  }

}
