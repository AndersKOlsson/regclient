import datetime
import pandas as pd

from decimal import Decimal

def job() -> pd.DataFrame:
    df = pd.DataFrame({
        'float_val': [1.0],
        'int_val': [1],
        'timestamp_val': [pd.Timestamp(year=2017, month=1, day=1, hour=12, tz="UTC")],
        'datetime_val': [pd.Timestamp('20180310', unit="us")],
        'string_val': ["string"],
        'date_val': [datetime.date(year=2017, month=1, day=1)],
        'time_val': [datetime.time(hour=12, minute=30, second=59, microsecond=123456)],
        'list_val': [[1, 2, 3]],
        'dict_val': [{'a': 1}],
        'json_val': ['{"a": 1}'],
        'numeric_val': [Decimal("3.123439")],
        'bignumeric_val': [Decimal("3.1234394934291287583931")],
        #'period': [pd.Period('2012-1-1', freq='D')],
        #'interval': [pd.Interval(left=0, right=5)],
        #'array': [pd.array([1, 2, 3])],
        # 'series': [pd.Series(np.random.randn(8), dtype="float16")],
        #'categorical': [pd.Categorical(['a', 'b', 'c', 'a', 'b', 'c'], categories=['c', 'b', 'a'])],
    })
    df["timestamp_val"] = df["timestamp_val"].astype("datetime64[us, UTC]")
    df["datetime_val"] = df["datetime_val"].astype("datetime64[us]")
    return df
