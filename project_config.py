import twirl

twirl.project_config(
    project_name="twirl-dev-anderstest",
    secrets=[twirl.EnvironmentSecrets()],
    datastores=[
        twirl.BigQueryDatastore(
            name="bq1",
            gcp_project_id="twirldata-dev",
            gcp_location="europe-west1",
        ),
    ],
    container_registry=twirl.GcpContainerRegistry(
        gcp_region="europe-west1",
        registry_host="europe-west1-docker.pkg.dev",
        gcp_project="twirldata-dev",
        registry_path="twirl",
    ),
    cloud_runtime=twirl.GcpCloudRuntime(
        project="twirldata-dev",
        location="europe-west1",
        job_runner_account="twirl-runner@twirldata-dev.iam.gserviceaccount.com",
        job_manager_account="twirl-job-manager@twirldata-dev.iam.gserviceaccount.com",
        default_job_resource_config=twirl.CloudRunResourceConfig(
            memory="1Gi", cpu_count=1
        ),
        max_retries=3,
    ),
    beam_runtime=twirl.GcpBeamRuntime(
        project="twirldata-dev",
        location="europe-west1",
        bucket="twirldata-dev",
        job_worker_account="twirl-runner@twirldata-dev.iam.gserviceaccount.com",
    ),
    #notify_on_failure=True,
    #notify_on_success=False,
)
