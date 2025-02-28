"""
read config
"""
from typing import Optional, Dict, List

from strictyaml import YAML, load
import yaml
from pathlib import Path
import pdfBot
import box


# project directories
PACKAGE_ROOT = Path(pdfBot.__file__).resolve().parent
CONFIG_FILE_PATH = PACKAGE_ROOT / "config.yaml"

def find_config_file() -> Path:
    """Locate the configration file"""
    if CONFIG_FILE_PATH.is_file():
        return CONFIG_FILE_PATH
    raise Exception(f"config not found at {CONFIG_FILE_PATH}")


def fetch_config_from_yaml(cfg_path: Optional[Path] = None) -> YAML:
    """parse YAML containing the package configration"""

    if not cfg_path:
        cfg_path = find_config_file()
    
    if cfg_path:
        with open(cfg_path, 'r') as fyaml:
            parsed_config = box.Box(yaml.safe_load(fyaml.read()))
            return parsed_config
        
    return OSError(f"Did not find config file at path: {cfg_path}")

def create_config():
    """create config"""

    config = fetch_config_from_yaml()
    if config is not None:
        return config

config = create_config()



